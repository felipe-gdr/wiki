package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.getAllCells().zip(initializer.initialPermutation).forEach { (cell, value) ->
            board[cell] = value
        }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean = board.getAllCells()
            .mapNotNull { board[it] }
            .withIndex()
            .all { (idx, value) -> value == idx + 1 }

    override fun processMove(direction: Direction) {
        board.moveTo(direction)
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}

fun GameBoard<Int?>.moveTo(direction: Direction) {
    val cell = this.getAllCells().find { cell ->
        val neighbour = cell.getNeighbour(direction) ?: return@find false
        this[neighbour] == null
    } ?: return

    val neighbour = cell.getNeighbour(direction) ?: return

    this[neighbour] = this[cell]
    this[cell] = null
}
