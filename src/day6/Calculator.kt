package day6

import java.io.File

class Calculator(val numbers: List<List<String>>, val operators: List<String>) {

    companion object {
        fun create(): Calculator {
            val n: MutableList<List<String>> = mutableListOf()
            val o: MutableList<String> = mutableListOf()
            var spaceList: List<Int> = listOf()
            var index: Int = 0
            File("D:\\Tools\\Repo\\aoc\\src\\day6\\input").forEachLine { line ->

                if (line.contains('+')) {
                    spaceList = countSpacesBetweenOperators(line)
                    o.addAll(line.split(' ').filterNot {
                        it.isEmpty()
                    })
                } else {
                    var list: MutableList<String> = mutableListOf()
                    spaceList.forEach {
                        list.add(line.substring(index, it+index))
                        index = index + it +1
                    }
                    n.add(list)
                    index = 0
                }
            }
            return Calculator(n, o)
        }

        fun countSpacesBetweenOperators(line: String): List<Int> {
            val result = mutableListOf<Int>()

            var i = 0
            while (i < line.length) {
                if (line[i] == '+' || line[i] == '*') {
                    val startOpIndex = i
                    i++

                    var spaceCount = 0
                    while (i < line.length && line[i] == ' ') {
                        spaceCount++
                        i++
                    }

                    if (i < line.length && (line[i] == '+' || line[i] == '*')) {
                        result.add(spaceCount)
                    }
                } else {
                    i++
                }
            }

            return result
        }
    }


    fun solve(): Long {
        val sumOf = IntRange(0, numbers[0].size - 1).map { r ->
            val operator: String = operators[r]
            val values = IntRange(0, numbers.size - 1).map { n ->
                numbers[n][r]
            }

            val largest = values.maxOf { it.length }

            val newValues = IntRange(0, largest - 1).map { l ->
                IntRange(0, values.size - 1).fold("") { acc, x ->
                    if (values[x][l].isWhitespace())
                        acc
                    else
                        acc + values[x][l]
                }
            }

            operation(newValues, operator.trim())
        }

        return sumOf.sumOf { it }
    }

    fun operation(values: List<String>, op: String): Long {
        return when (op) {
            "+" -> values.map { it.toInt() }.fold(0) { acc, i -> acc + i }
            "-" -> values.map { it.toInt() }.fold(0) { acc, i -> acc - i }
            "*" -> values.map { it.toInt() }.fold(1) { acc, i -> acc * i }
            else -> {
                0L
            }
        }
    }
}