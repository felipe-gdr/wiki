package taxipark

import kotlin.math.floor

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers.filter { driver -> trips.none { it.driver == driver } }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        allPassengers.filter { passenger ->
            trips.flatMap { it.passengers }
                    .filter { it == passenger }
                    .size >= minTrips
        }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        trips.filter { it.driver == driver }
                .flatMap { it.passengers }
                .groupBy { it }
                .filter { (_, trips) -> trips.size > 1 }
                .map { (passenger, _) -> passenger }
                .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        trips.flatMap { trip -> trip.passengers.map { Pair(it, trip.discount != null) } }
                .groupBy { it.first }
                .mapValues { (_, value) ->
                    val (withDiscount, noDiscount) = value.partition { it.second }

                    Pair(withDiscount.size, noDiscount.size)
                }
                .filter { (_, value) -> value.first > value.second }
                .map { it.key }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? =
        trips.groupBy { floor(it.duration / 10.0).toInt() }
                .mapValues { (_, value) -> value.size }
                .mapKeys { (key, _) -> (key * 10) until (key + 1) * 10 }
                .maxBy { (_, value) -> value }
                ?.key

/*
 * Task #6
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.map { it.cost }.sum()
    val incomePerDriver = trips.groupBy { it.driver }
            .map { (_, value) -> value.sumByDouble { it.cost } }
            .sortedDescending()

    val twentyPercentCount = (allDrivers.size * 0.2).toInt()

    val twentyPercentIncome = incomePerDriver.subList(0, twentyPercentCount).sum()

    return twentyPercentIncome >= (totalIncome * 0.8)
}