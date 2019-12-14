package wiki.adventofcode2019.day5.part2

import java.io.File
import java.net.URL

/**
 *
 * --- Part Two ---
The air conditioner comes online! Its cold air feels good for a while, but then the TEST alarms start to go off. Since the air conditioner can't vent its heat anywhere but back into the spacecraft, it's actually making the air inside the ship warmer.

Instead, you'll need to use the TEST to extend the thermal radiators. Fortunately, the diagnostic program (your puzzle input) is already equipped for this. Unfortunately, your Intcode computer is not.

Your computer is only missing a few opcodes:

Opcode 5 is jump-if-true: if the first parameter is non-zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
Opcode 6 is jump-if-false: if the first parameter is zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
Opcode 7 is less than: if the first parameter is less than the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
Opcode 8 is equals: if the first parameter is equal to the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
Like all instructions, these instructions need to support parameter modes as described above.

Normally, after an instruction is finished, the instruction pointer increases by the number of values in that instruction. However, if the instruction modifies the instruction pointer, that value is used and the instruction pointer is not automatically increased.

For example, here are several programs that take one input, compare it to the value 8, and then produce one output:

3,9,8,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
3,9,7,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
3,3,1108,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
3,3,1107,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:

3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 (using position mode)
3,3,1105,-1,9,1101,0,0,12,4,12,99,1 (using immediate mode)
Here's a larger example:

3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99
The above example program uses an input instruction to ask for a single number. The program will then output 999 if the input value is below 8, output 1000 if the input value is equal to 8, or output 1001 if the input value is greater than 8.

This time, when the TEST diagnostic program runs its input instruction to get the ID of the system to test, provide it 5, the ID for the ship's thermal radiator controller. This diagnostic test suite only outputs one number, the diagnostic code.

What is the diagnostic code for system ID 5?
 */

fun main() {
    val numInput = readLine()!!.toInt()
//    val rawInput = "3,9,8,9,10,9,4,9,99,-1,8"
//    val rawInput = "3,9,7,9,10,9,4,9,99,-1,8"
//    val rawInput = "3,3,1108,-1,8,3,4,3,99"
//    val rawInput = "3,3,1107,-1,8,3,4,3,99"
//    val rawInput = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"
//    val rawInput = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1"
//    val rawInput = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"


    val resource: URL = object {}.javaClass.getResource("/day5/input.txt")
    val rawInput = File(resource.toURI()).readText()

    val input = rawInput.split(",").map { it.toInt() }.toMutableList()

    var currentIndex = 0

    while (currentIndex >= 0) {
        currentIndex = step(input, currentIndex, numInput)
    }
}

fun step(items: MutableList<Int>, index: Int, input: Int): Int {
    try {
        val instruction = items[index].toString().padStart(5, '0')
        val operation = instruction.subSequence(3, 5)
        val paramMode1 = instruction[2]
        val paramMode2 = instruction[1]

        fun getParam(mode: Char, value: Int) =
            if (mode == '0') items[value] else if (mode == '1') value else throw RuntimeException("Invalid parameter mode $mode")

        if (operation == "99") {
            return -1
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

            return index + 4
        } else if (operation == "03" || operation == "04") {
            val n1 = items[index + 1]

            if (operation == "03") {
                items[n1] = input
            } else {
                println("output: $index:${getParam(paramMode1, n1)}")
            }

            return index + 2
        } else if (operation == "05" || operation == "06") {
            val param1 = getParam(paramMode1, items[index + 1])
            val param2 = getParam(paramMode2, items[index + 2])

            if (operation == "05" && param1 != 0) {
                return param2
            }

            if (operation == "06" && param1 == 0) {
                return param2
            }
            return index + 3
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

            return index + 4
        }

        throw RuntimeException("Invalid operation $operation. index $index")

    } catch (error: Exception) {
        println(error)
    }

    return -1
}
