package wiki.adventofcode2019.day4

/**
 * --- Day 4: Secure Container ---
You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password on a sticky note, but someone threw it out.

However, they do remember a few key facts about the password:

It is a six-digit number.
The value is within the range given in your puzzle input.
Two adjacent digits are the same (like 22 in 122345).
Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
Other than the range rule, the following are true:

111111 meets these criteria (double 11, never decreases).
223450 does not meet these criteria (decreasing pair of digits 50).
123789 does not meet these criteria (no double).
How many different passwords within the range given in your puzzle input meet these criteria?

Your puzzle input is 178416-676461.
 */

fun hasAdjacent(num: Int): Boolean {
    val digits = num.toString().split("").filter { it != "" }

    for (x in 0..digits.size - 2) {
        val n1 = digits[x]
        val n2 = digits[x + 1]

        if (n1 == n2) {
            return true
        }
    }

    return false
}

fun hasAdjacentPart2(num: Int): Boolean {
    val digits = num.toString().split("").filter { it != "" }.map { it.toInt() }

    fun check(num: Int, pointer: Int): Pair<Int, Int> {
        var repeats = 1
        var pointerVar = pointer

        while (num == digits[pointerVar++]) {
            repeats++
            if(pointerVar == digits.size) break
        }

        return Pair(repeats, pointerVar)
    }

    var pointer = 1
    var digit = digits[0]
    val pairs = emptyList<Pair<Int, Int>>().toMutableList()

    do {
        val (repeats, newPointer) = check(digit, pointer)
        pairs.add(Pair(digit, repeats))
        pointer = newPointer
        digit = digits[pointer - 1]
    } while (pointer < digits.size)

    return pairs.any { it.second == 2 }
}

fun areDigitsMonotonic(num: Int): Boolean {
    val digits = num.toString().split("").filter { it != "" }.map { it.toInt() }

    var previous = digits[0]

    for (x in 1 until digits.size) {
        if (digits[x] < previous) {
            return false
        }
        previous = digits[x]
    }

    return true;
}

fun meetsCriteria(num: Int): Boolean = hasAdjacent(num) && areDigitsMonotonic(num)
fun meetsCriteriaPart2(num: Int): Boolean = hasAdjacentPart2(num) && areDigitsMonotonic(num)

fun main() {
    val range = 178416..676461

//    println(hasAdjacentPart2(112233))
//    println(hasAdjacentPart2(123444))
//    println(hasAdjacentPart2(111122))

    val result1 = range.filter { meetsCriteria(it) }.size
    val result2 = range.filter { meetsCriteriaPart2(it) }.size

    println(result1)
    println(result2)

}

