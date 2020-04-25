package board

import board.Direction.*

open class SquareBoardImpl(override val width: Int) : SquareBoard {
    private val cells = Array(width) { i ->
        Array(width) { j -> Cell(i + 1, j + 1) }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i > width || j > width) null
        else cells[i - 1][j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (i > width || j > width) throw IllegalArgumentException("Invalid cell (${i}, ${j})")
        else cells[i - 1][j - 1]
    }

    override fun getAllCells(): Collection<Cell> {
        val result = mutableListOf<Cell>();

        for (i in 0 until width) {
            for (j in 0 until width) {
                result.add(cells[i][j])
            }
        }

        return result
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val result = mutableListOf<Cell>();

        for (j in jRange) {
            if (j <= width) {
                result.add(cells[i - 1][j - 1])
            }
        }

        return result
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val results = mutableListOf<Cell>();

        for (i in iRange) {
            if (i <= width) {
                results.add(cells[i - 1][j - 1])
            }
        }

        return results
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        if (direction == UP && this.i == 1) return null
        if (direction == LEFT && this.j == 1) return null
        if (direction == DOWN && this.i == width) return null
        if (direction == RIGHT && this.j == width) return null

        return when (direction) {
            UP -> cells[this.i - 2][this.j - 1]
            LEFT -> cells[this.i - 1][this.j - 2]
            DOWN -> cells[this.i][this.j - 1]
            RIGHT -> cells[this.i - 1][this.j]
        }
    }

}

class GameBoardImpl<T>(override val width: Int, private val squareBoard: SquareBoard) : SquareBoard by squareBoard, GameBoard<T> {
    private val values = mutableMapOf<Cell, T?>()

    init {
        this.getAllCells().forEach { cell ->
            values[cell] = null
        }
    }

    override fun get(cell: Cell): T? {
        return values[cell]
    }

    override fun set(cell: Cell, value: T?) {
        values[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return values.filterValues(predicate).keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return this.filter(predicate).firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return !this.filter(predicate).isEmpty()
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return this.values.all { (_, value) -> predicate(value) }
    }

}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width, SquareBoardImpl(width))

