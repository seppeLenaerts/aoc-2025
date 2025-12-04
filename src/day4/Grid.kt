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

        var updatedCells : List<Cell> = cells
        var acc = 0

        while (anyAccess(updatedCells)) {
            updatedCells = updatedCells.map {
                if(hasAccess(it, updatedCells)) {
                    acc += 1
                    Cell(it.x, it.y, '.')
                } else {
                    it
                }
            }
        }
        return acc
    }

    fun anyAccess(cells: List<Cell>) : Boolean {
        return cells.map {
            hasAccess(it, cells)
        }.contains(true)
    }


    fun hasAccess(cell: Cell, list: List<Cell>) : Boolean {
        if (cell.char == '.') {
            return false
        }
        val around = IntRange(-1, 1).sumOf { xx ->
            IntRange(-1, 1).map { yy ->
                if (findCell(cell.x + xx, cell.y + yy, list).char == '.') {
                    0
                } else {
                    1
                }
            }.sum()
        }
        return around <= 4
    }

    fun findCell(x: Int, y: Int, list: List<Cell>) : Cell {
        return list.find { cell -> cell.x == x && cell.y == y } ?: Cell(0,0,'.')
    }

}
