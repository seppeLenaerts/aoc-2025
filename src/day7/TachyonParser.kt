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
}