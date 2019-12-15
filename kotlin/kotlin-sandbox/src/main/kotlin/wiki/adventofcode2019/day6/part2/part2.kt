package wiki.adventofcode2019.day6.part2

import java.io.File
import java.net.URL
import java.util.*
import kotlin.math.max

/**
 * --- Part Two ---
Now, you just need to figure out how many orbital transfers you (YOU) need to take to get to Santa (SAN).

You start at the object YOU are orbiting; your destination is the object SAN is orbiting. An orbital transfer lets you move from any object to an object orbiting or orbited by that object.

For example, suppose you have the following map:

COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
K)YOU
I)SAN
Visually, the above map of orbits looks like this:

YOU
/
G - H       J - K - L
/           /
COM - B - C - D - E - F
\
I - SAN
In this example, YOU are in orbit around K, and SAN is in orbit around I. To move from K to I, a minimum of 4 orbital transfers are required:

K to J
J to E
E to D
D to I
Afterward, the map of orbits looks like this:

G - H       J - K - L
/           /
COM - B - C - D - E - F
\
I - SAN
\
YOU
What is the minimum number of orbital transfers required to move from the object YOU are orbiting to the object SAN is orbiting? (Between the objects they are orbiting - not between YOU and SAN.)
 */

val testInput = """
    COM)B
    B)C
    C)D
    D)E
    E)F
    B)G
    G)H
    D)I
    E)J
    J)K
    K)L
    K)YOU
    I)SAN
""".trimIndent()

fun navigate(
    current: Pair<String, String>,
    all: List<Pair<String, String>>,
    partialPath: List<String>
): List<List<String>> {
    val next = all.filter { current.second == it.first }

    if (next.isEmpty()) {
        return listOf(partialPath.plus(current.second))
    }

    val paths = emptyList<List<String>>().toMutableList()

    for (n in next) {
        val list = navigate(n, all, partialPath.plus(current.second))

        paths.addAll(list)
    }

    return paths
}

fun main() {
//    val input = testInput

    val resource: URL = object {}.javaClass.getResource("/day6/input.txt")
    val rawInput = File(resource.toURI()).readText()

    val input = rawInput

    val pairs = input.split("\n")
        .map { it.split(")") }
        .map { Pair(it[0], it[1]) }

    val com = pairs.find { it.first == "COM" }!!

    val result = navigate(com, pairs, emptyList())

    val me = result.find { it.last() == "YOU" }!!.toMutableList()
    val santa = result.find { it.last() == "SAN" }!!.toMutableList()

    val largest = max(me.size, santa.size)

    while (me.size != largest) {
        me.add("FIL")
    }

    while (santa.size != largest) {
        santa.add("FIL")
    }

    val lastCommon = me.zip(santa).findLast { it.first == it.second }!!.first

    val indexOfLastCommon = me.indexOf(lastCommon)

    val me2 = me.subList(indexOfLastCommon + 1, me.size).filter { it != "FIL" }.filter { it != "YOU" }
    val santa2 = santa.subList(indexOfLastCommon + 1, santa.size).filter { it != "FIL" }.filter { it != "SAN" }

    val r = me2.size + santa2.size

    println(r)

}

