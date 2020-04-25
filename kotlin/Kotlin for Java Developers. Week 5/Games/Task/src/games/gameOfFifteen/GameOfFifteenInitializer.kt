package games.gameOfFifteen

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val permutation = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).shuffled()

        if (isEven(permutation)) {
            listOf(listOf(permutation[1]), permutation.take(1), permutation.takeLast(13))
                    .flatten()
        } else {
            permutation
        }
    }
}

class EasyGameInitializer : GameOfFifteenInitializer {
    override val initialPermutation by lazy {
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 12, 13, 14)
    }
}

