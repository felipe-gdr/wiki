package wiki.adventofcode2019.day8.part1

import java.io.File
import java.net.URL

fun main() {
//    val input = "123456789012"

    val resource: URL = object {}.javaClass.getResource("/day8/input.txt")
    val input = File(resource.toURI()).readText()

    val dimensions = Pair(25, 6)
    val layerSize = dimensions.first * dimensions.second
    val layersCount = input.length / layerSize
    val layersRange = 0 until layersCount

    val mapIndexed = layersRange.map { layer ->
        input
            .substring(layer * layerSize, (layer + 1) * layerSize)
            .split("")
            .filter { it != "" }
            .map { it.toInt() }
    }

    val layerWithMostZeroes = mapIndexed.sortedBy { layer -> layer.filter { it == 0 }.size }[0]

    val oneCount = layerWithMostZeroes.filter { it == 1 }.size
    val twoCount = layerWithMostZeroes.filter { it == 2 }.size

    val result = oneCount * twoCount

    println(result)

}