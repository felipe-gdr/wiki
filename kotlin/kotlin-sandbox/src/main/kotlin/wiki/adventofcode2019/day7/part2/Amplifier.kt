package wiki.adventofcode2019.day7.part2

import java.util.*

class Amplifier(private val index: Int, phaseSetting: Int, programParam: List<Int>) {
    private var currentIndex = 0
    var output = 0
    private val inputs = ArrayDeque<Int>()
    private val program = programParam.toMutableList()

    init {
        inputs.offer(phaseSetting)
    }

    fun run(input: Int): Int {

        inputs.offer(input)

        loop@ while (currentIndex >= 0) {
            val stepOutput = step(program, currentIndex, inputs)

            when (stepOutput.first) {
                "next" -> currentIndex = stepOutput.second
                "output" -> {
                    currentIndex += 2
                    output = stepOutput.second
                    return output
                }
                "exit" -> return -1
                else -> throw RuntimeException("unexpected output ${stepOutput.first}")
            }
        }

        throw RuntimeException("Program had no output")
    }

    private fun step(items: MutableList<Int>, index: Int, inputs: Queue<Int>): Pair<String, Int> {
        try {
            val instruction = items[index].toString().padStart(5, '0')
            val operation = instruction.subSequence(3, 5)
            val paramMode1 = instruction[2]
            val paramMode2 = instruction[1]

            fun getParam(mode: Char, value: Int) =
                if (mode == '0') items[value] else if (mode == '1') value else throw RuntimeException("Invalid parameter mode $mode")

            if (operation == "99") {
                return Pair("exit", -1)
            }

            if (operation == "01" || operation == "02") {
                val param1 = getParam(paramMode1, items[index + 1])
                val param2 = getParam(paramMode2, items[index + 2])
                val output = items[index + 3]

                val result =
                    when (operation) {
                        "01" -> param1 + param2
                        "02" -> param1 * param2
                        else -> throw RuntimeException(
                            "Invalid operation $operation"
                        )
                    }

                items[output] = result

                return Pair("next", index + 4)
            } else if (operation == "03" || operation == "04") {
                val n1 = items[index + 1]

                return if (operation == "03") {
                    items[n1] = inputs.poll()!!
                    Pair("next", index + 2)
                } else {
//                    println(getParam(paramMode1, n1))
                    Pair("output", getParam(paramMode1, n1))
                }

            } else if (operation == "05" || operation == "06") {
                val param1 = getParam(paramMode1, items[index + 1])
                val param2 = getParam(paramMode2, items[index + 2])

                if (operation == "05" && param1 != 0) {
                    return Pair("next", param2)
                }

                if (operation == "06" && param1 == 0) {
                    return Pair("next", param2)
                }
                return Pair("next", index + 3)
            } else if (operation == "07" || operation == "08") {
                val param1 = getParam(paramMode1, items[index + 1])
                val param2 = getParam(paramMode2, items[index + 2])
                val output = items[index + 3]

                if (operation == "07") {
                    items[output] = if (param1 < param2) 1 else 0
                }

                if (operation == "08") {
                    items[output] = if (param1 == param2) 1 else 0
                }

                return Pair("next", index + 4)
            }

            throw RuntimeException("Invalid operation $operation. index $index")

        } catch (error: Exception) {
            println(error)
        }

        throw RuntimeException("commands over without exit code (99)")
    }

    override fun toString(): String {
        return "Amp $index"
    }
}