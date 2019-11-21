package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun String.count(ch: Char): Int {
    return this.map { ch2 -> ch == ch2 }.filter { ch2 -> ch2 }.size
}

fun String.isRight(ch: Char, idx: Int): Boolean = this[idx] == ch

fun String.evaluate(guess: String): Evaluation {
    val valid = 'A'..'F'

    val results = valid
            .zip(Array(valid.count()) { IntArray(3) { 0 } })
            .toMap()

    results.forEach { entry -> entry.value[0] = count(entry.key) }

    for ((index, ch) in guess.toCharArray().withIndex()) {
        val contains = contains(ch)
        val isRight = isRight(ch, index)

        if (isRight) {
            results[ch]!![1] = results[ch]!![1] + 1
        } else if (contains) {
            results[ch]!![2] = results[ch]!![2] + 1
        }
    }

    var positions = 0
    var rights = 0

    results.forEach(fun(entry: Map.Entry<Char, IntArray>) {
        val count  = entry.value[0]
        val right  = entry.value[1]
        val position  = entry.value[2]
        val diff = count - right

        positions += Math.min(diff, position)
        rights += right
    })

    return Evaluation(rights, positions)
}

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return secret.evaluate(guess)
}
