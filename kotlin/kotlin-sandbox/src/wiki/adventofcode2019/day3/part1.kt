package wiki.adventofcode2019.day3

import java.io.File
import java.lang.RuntimeException
import java.net.URL
import kotlin.math.abs

/**
--- Day 3: Crossed Wires ---
The gravity assist was successful, and you're well on your way to the Venus refuelling station. During the rush back on Earth, the fuel management system wasn't completely installed, so that's next on the priority list.

Opening the front panel reveals a jumble of wires. Specifically, two wires are connected to a central port and extend outward on a grid. You trace the path each wire takes as it leaves the central port, one wire per line of text (your puzzle input).

The wires twist and turn, but the two wires occasionally cross paths. To fix the circuit, you need to find the intersection point closest to the central port. Because the wires are on a grid, use the Manhattan distance for this measurement. While the wires do technically cross right at the central port where they both start, this point does not count, nor does a wire count as crossing with itself.

For example, if the first wire's path is R8,U5,L5,D3, then starting from the central port (o), it goes right 8, up 5, left 5, and finally down 3:

...........
...........
...........
....+----+.
....|....|.
....|....|.
....|....|.
.........|.
.o-------+.
...........
Then, if the second wire's path is U7,R6,D4,L4, it goes up 7, right 6, down 4, and left 4:

...........
.+-----+...
.|.....|...
.|..+--X-+.
.|..|..|.|.
.|.-X--+.|.
.|..|....|.
.|.......|.
.o-------+.
...........
These wires cross at two locations (marked X), but the lower-left one is closer to the central port: its distance is 3 + 3 = 6.

Here are a few more examples:

R75,D30,R83,U83,L12,D49,R71,U7,L72
U62,R66,U55,R34,D71,R55,D58,R83 = distance 159
R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = distance 135
What is the Manhattan distance from the central port to the closest intersection?
 */

fun movements(wire: String): List<Pair<Int, Int>> {
    var cursor = Pair(0, 0)
    val result = emptyList<Pair<Int, Int>>().toMutableList()

    result.add(Pair(cursor.first, cursor.second))

    wire.split(",").forEach { movement ->
        val action = movement[0]

        val points = movement.replace(Regex("[UDRL]"), "").toInt()

        when (action) {
            'U' -> {
                cursor = Pair(cursor.first, cursor.second + points)
            }
            'D' -> {
                cursor = Pair(cursor.first, cursor.second - points)
            }
            'R' -> {
                cursor = Pair(cursor.first + points, cursor.second)
            }
            'L' -> {
                cursor = Pair(cursor.first - points, cursor.second)
            }
            else -> {
                throw RuntimeException("Invalid movement $action")
            }
        }

        result.add(Pair(cursor.first, cursor.second))
    }

    return result
}

fun checkStep(start: Pair<Int, Int>, end: Pair<Int, Int>, otherWire: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val crossings = emptyList<Pair<Int, Int>>().toMutableList()
    var distance = 0;

    for (x in 0..otherWire.size - 2) {
        val myStartX = start.first
        val myStartY = start.second
        val myEndX = end.first
        val myEndY = end.second

        val otherStartX = otherWire[x].first
        val otherStartY = otherWire[x].second
        val otherEndX = otherWire[x + 1].first
        val otherEndY = otherWire[x + 1].second

        if (otherStartX == otherEndX && myStartY == myEndY && !(otherStartX == 0 && otherStartY == 0) &&
            (
                    ((myStartX <= otherStartX && myEndX >= otherStartX) || (myStartX >= otherStartX && myEndX <= otherStartX))
                            &&
                            ((otherStartY <= myStartY && otherEndY >= myStartY) || (otherStartY >= myStartY && otherEndY <= myStartY))
                    )
        ) {
            distance += abs(otherStartY - myEndY)

            crossings.add(Pair(abs(otherStartX) + abs(myEndY), distance))
        } else if (
            otherStartY == otherEndY && myStartX == myEndX && !(otherStartX == 0 && otherStartY == 0) &&
            (
                    ((myStartY <= otherStartY && myEndY >= otherStartY) || (myStartY >= otherStartY && myEndY <= otherStartY))
                            &&
                            ((otherStartX <= myStartX && otherEndX >= myStartX) || (otherStartX >= myStartX && otherEndX <= myStartX))
                    )
        ) {
            distance += abs(otherStartX - myEndX)

            crossings.add(Pair(abs(myStartX) + abs(otherEndY), distance))
        } else {
            distance += abs(otherEndX - otherStartX + otherEndY - otherStartY)
        }

    }

    return crossings
}

fun exec(input: String): Pair<Int, Int> {
    val lines: List<String> = input.split("\n")
    val wire1 = movements(lines[0])
    val wire2 = movements(lines[1])

    val crossings1 = emptyList<List<Pair<Int, Int>>>().toMutableList()
    val crossings2 = emptyList<List<Pair<Int, Int>>>().toMutableList()

    for (x in 0..wire1.size - 2) {
        crossings1.add(checkStep(wire1[x], wire1[x + 1], wire2))

    }
    for (x in 0..wire2.size - 2) {
        crossings2.add(checkStep(wire2[x], wire2[x + 1], wire1))
    }

    val crossings1Flatten = crossings1.flatten().filter { it.first != 0 }
    val crossings2Flatten = crossings2.flatten().filter { it.first != 0 }

    val bestCross = crossings1Flatten.sortedBy { it.first }[0].first

    val earliestCross = crossings1Flatten
        .map {
            Pair(
                it.second,
                crossings2Flatten
                    .filter { it2 -> it2.first == it.first }
                    .sortedBy { it2 -> it2.second }
                    .map { it2 -> it2.second }
                        [0]
            )
        }
        .map { it.first + it.second }
        .sorted()[0]

    return Pair(bestCross, earliestCross)
}

fun tests() {
    val wire1 = "R8,U5,L5,D3"
    val wire2 = "U7,R6,D4,L4"

    println(exec(wire1 + "\n" + wire2))
    println(exec("R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83"))
    println(exec("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7"))
}

fun main() {
    tests()
    val resource: URL = object {}.javaClass.getResource("input.txt")
    val rawInput = File(resource.toURI()).readText()
    println(exec(rawInput))
}


