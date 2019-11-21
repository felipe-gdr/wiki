package mastermind

data class EvaluationFn(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuessFn(secret: String, guess: String): EvaluationFn {

    val rightPositions = secret.zip(guess).count { pair -> pair.first == pair.second  }

    val commonLetters = "ABCDEF".sumBy { ch ->

        Math.min(secret.count { it == ch }, guess.count { it == ch })
    }

    return EvaluationFn(rightPositions, commonLetters - rightPositions)
}

fun main(args: Array<String>) {
    val result = EvaluationFn(rightPosition = 1, wrongPosition = 1)
    println(evaluateGuessFn("BCDF", "ACEB").equals(result))
    println(evaluateGuessFn("AAAF", "ABCA").equals(result))
    println(evaluateGuessFn("ABCA", "AAAF").equals(result))
}