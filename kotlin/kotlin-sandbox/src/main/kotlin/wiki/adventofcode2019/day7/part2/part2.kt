package wiki.adventofcode2019.day7.part2

import java.io.File
import java.net.URL
import java.util.*
import kotlin.math.max

/**
 *--- Part Two ---
It's no good - in this configuration, the amplifiers can't generate a large enough output signal to produce the thrust you'll need. The Elves quickly talk you through rewiring the amplifiers into a feedback loop:

O-------O  O-------O  O-------O  O-------O  O-------O
0 -+->| Amp A |->| Amp B |->| Amp C |->| Amp D |->| Amp E |-.
|  O-------O  O-------O  O-------O  O-------O  O-------O |
|                                                        |
'--------------------------------------------------------+
|
v
(to thrusters)
Most of the amplifiers are connected as they were before; amplifier A's output is connected to amplifier B's input, and so on. However, the output from amplifier E is now connected into amplifier A's input. This creates the feedback loop: the signal will be sent through the amplifiers many times.

In feedback loop mode, the amplifiers need totally different phase settings: integers from 5 to 9, again each used exactly once. These settings will cause the Amplifier Controller Software to repeatedly take input and produce output many times before halting. Provide each amplifier its phase setting at its first input instruction; all further input/output instructions are for signals.

Don't restart the Amplifier Controller Software on any amplifier during this process. Each one should continue receiving and sending signals until it halts.

All signals sent or received in this process will be between pairs of amplifiers except the very first signal and the very last signal. To start the process, a 0 signal is sent to amplifier A's input exactly once.

Eventually, the software on the amplifiers will halt after they have processed the final loop. When this happens, the last output signal from amplifier E is sent to the thrusters. Your job is to find the largest output signal that can be sent to the thrusters using the new phase settings and feedback loop arrangement.

Here are some example programs:

Max thruster signal 139629729 (from phase setting sequence 9,8,7,6,5):

3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5
Max thruster signal 18216 (from phase setting sequence 9,7,8,5,6):

3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10
Try every combination of the new phase settings on the amplifier feedback loop. What is the highest signal that can be sent to the thrusters?
 */

fun permutations(list: List<Int>): List<List<Int>> {
    if (list.size == 1) return listOf(list)

    val result = emptyList<List<Int>>().toMutableList()

    for (x in list.indices) {
        val arr = list.toMutableList()

        arr.removeAt(x)

        permutations(arr).forEach { result.add(it.plus(list[x])) }
    }

    return result
}

fun main() {

    val phaseCombinations = permutations(listOf(5, 6, 7, 8, 9))

    val rawProgram = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
//    val rawProgram = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"
//    val rawProgram = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
//
//    val resource: URL = object {}.javaClass.getResource("/day7/input.txt")
//    val rawProgram = File(resource.toURI()).readText()
    val program = rawProgram.split(",").map { it.toInt() }.toMutableList()

//    var result = Int.MIN_VALUE
//
//    for (phaseSettings in phaseCombinations) {
//        result = max(
//            result,
//            attemptPhaseCombination(phaseSettings, program)
//        )
//    }

    var result = attemptPhaseCombination(listOf(9,8,7,6,5), program)

    println(result)

}

fun attemptPhaseCombination(phaseSettings: List<Int>, program: MutableList<Int>): Int {
    var input = 0
    var pointer = 0

    while (input != -1) {
        val phaseSetting = phaseSettings[pointer % phaseSettings.size]

        input = runAmplifier(program, phaseSetting, input)

        pointer++
    }

    return input
}

fun runAmplifier(program: MutableList<Int>, phaseSetting: Int, input: Int): Int {

    var currentIndex = 0

    val inputs = ArrayDeque<Int>()

    inputs.offer(phaseSetting)
    inputs.offer(input)

    loop@ while (currentIndex >= 0) {
        val stepOutput = step(program, currentIndex, inputs)

        when (stepOutput.first) {
            "next" -> currentIndex = stepOutput.second
            "output" -> return stepOutput.second
            "exit" -> return -1
            else -> throw RuntimeException("unexpected output ${stepOutput.first}")
        }
    }

    throw RuntimeException("Program had no output")
}

fun step(items: MutableList<Int>, index: Int, inputs: Queue<Int>): Pair<String, Int> {
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

            if (operation == "03") {
                items[n1] = inputs.poll()!!
            } else {
                return Pair("output", getParam(paramMode1, n1))
            }

            return Pair("next", index + 2)
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
