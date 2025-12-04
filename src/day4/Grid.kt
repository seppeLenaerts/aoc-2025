package day4

import java.io.File

data class Grid(val cells: List<Cell>) {

    companion object {
        fun parse(): Grid {
            return Grid(
                File("/Users/seppe.lenaerts/Documents/Repo/aoc/src/day4/input")
                    .readLines()
                    .mapIndexed { index, string -> string.mapIndexed { i, ch -> Cell(i, index, ch) } }
                    .flatten()
            )
        }
    }

    fun howMany() : Int {
        return cells.map { if(hasAccess(it)) 1 else 0 }.sum()
    }

    fun hasAccess(cell: Cell) : Boolean {
        if (cell.char == '.') {
            return false
        }
        val around = IntRange(-1, 1).sumOf { xx ->
            IntRange(-1, 1).map { yy ->
                if (findCell(cell.x + xx, cell.y + yy).char == '.') {
                    0
                } else {
                    1
                }
            }.sum()
        }
        return around <= 4
    }

    fun findCell(x: Int, y: Int) : Cell {
        return cells.find { cell -> cell.x == x && cell.y == y } ?: Cell(0,0,'.')
    }

}
