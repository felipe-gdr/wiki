package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    val inversions = permutation.windowed(size = permutation.size, step = 1, partialWindows = true)
            .map { window ->
                val num = window.first()
                val others = window.takeLast(window.size - 1)

                others.count { num > it }
            }
            .sum()

    return inversions % 2 == 0
}