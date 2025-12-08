package day7

import java.io.File

data class TachyonParser(val lines: MutableList<MutableList<Char>>) {

    companion object {
        fun create(): TachyonParser {
            return TachyonParser(File("D:\\Tools\\Repo\\aoc\\src\\day7\\input").readLines().map { it.toMutableList() }.toMutableList())
        }
    }

    fun splitBeams(): Int {
        var index = 1
        var splits = 0

        while (index < lines.size) {
            val toPipe = mutableListOf<Int>()
            lines[index].mapIndexed { i, ch ->
                if (ch == '^' && lines[index-1][i] == '|') {
                    if (i-1 >= 0) {
                        toPipe.add(i-1)
                    }
                    if (i+1 <= lines[index].size) {
                        toPipe.add(i+1)
                    }
                    splits++
                }
            }
            toPipe.forEach { lines[index][it] = '|' }
            toPipe.clear()
            lines.forEach { println(it) }
            lines[index].mapIndexed { i, ch ->
                if ((lines[index - 1][i] == '|' || lines[index-1][i] == 'S') && lines[index][i] != '^') {
                    toPipe.add(i)
                }
            }
            toPipe.forEach { lines[index][it] = '|' }
            lines.forEach { println(it) }
            index++
        }
        return splits
    }

    fun partTwo(): Long {
        val rows = lines.size
        val cols = lines[0].size

        var startRow = -1
        var startCol = -1
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (lines[r][c] == 'S') {
                    startRow = r
                    startCol = c
                    break
                }
            }
            if (startRow != -1) break
        }

        val memo = Array(rows) { LongArray(cols) { -1 } }

        fun countPaths(r: Int, c: Int): Long {
            if (c !in 0..<cols) return 0
            if (r == rows - 1) return 1
            if (memo[r][c] != -1L) return memo[r][c]

            var paths = 0L
            val cell = lines[r][c]

            if (cell == '^') {
                paths += countPaths(r + 1, c - 1)
                paths += countPaths(r + 1, c + 1)
            } else {
                paths += countPaths(r + 1, c)
            }

            memo[r][c] = paths
            return paths
        }

        return countPaths(startRow, startCol)
    }
}