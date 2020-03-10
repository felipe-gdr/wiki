package wiki.adventofcode2019.day8.part2

import java.io.File
import java.net.URL

fun main() {
//    val input = "0222112222120000"

    val resource: URL = object {}.javaClass.getResource("/day8/input.txt")
    val input = File(resource.toURI()).readText()

    val dimensions = Pair(25, 6)
    val layerSize = dimensions.first * dimensions.second
    val layersCount = input.length / layerSize
    val layersRange = 0 until layersCount

    val layers = layersRange.map { layer ->
        input
            .substring(layer * layerSize, (layer + 1) * layerSize)
            .split("")
            .filter { it != "" }
            .map { it.toInt() }
    }

    val imageArray = emptyList<Int>().toMutableList()

    for (x in 0 until layerSize) {
        layers
            .find { layer -> layer[x] != 2 }
            ?.get(x)
            ?.let { imageArray.add(it) }
    }

    val imageMatrix = emptyList<List<Int>>().toMutableList()

    for (x in 0 until dimensions.second) {
        imageMatrix.add(
            imageArray.slice(x * dimensions.first until (x + 1) * dimensions.first)
        )
    }

    print(imageMatrix)
}

fun print(matrix: List<List<Int>>) {
    matrix.forEach { row ->
        println(
            row.joinToString("")
                .replace('0', ' ')
                .replace('1', '*')
        )
    }
}