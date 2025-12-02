package day2

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

data class ProductCode(val startId : Id, val endId: Id) {

    companion object {
        fun scanInput() : List<ProductCode> {
            val result : List<ProductCode> = mutableListOf()
            Path("/Users/seppe.lenaerts/Documents/Repo/aoc/src/day2/input").forEachLine {
                it.split(',')
                    .map { s -> parseCode(s.split('-')) }
                    .forEach { code -> result.plus(code) }
            }
            return result
        }

        fun parseCode(codes: List<String>) : ProductCode {
            return ProductCode(Id(codes[0]), Id(codes[1]))
        }
    }

    fun createIds() : List<Id> {
        if (startId.toInt() > endId.toInt()) {
            return listOf()
        }
        val result : List<Id> = mutableListOf()
        for (i in startId.toInt()..endId.toInt()) {
            result.plus(Id(i.toString()))
        }
        return result
    }
}