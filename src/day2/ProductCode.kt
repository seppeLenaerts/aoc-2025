package day2

import java.io.File

data class ProductCode(val startId: Id, val endId: Id) {

    companion object {
        fun scanInput(): List<ProductCode> {
            return File("D:\\Tools\\Repo\\aoc\\src\\day2\\input")
                .readText()
                .split(',')
                .map { s -> parseCode(s.split('-')) }
        }

        fun parseCode(codes: List<String>): ProductCode {
            return ProductCode(Id(codes[0]), Id(codes[1]))
        }
    }

    fun validate(): Long {
        return LongRange(startId.toLong(), endId.toLong())
            .map { Id(it.toString()) }
            .fold(0) { acc, id ->
                if (id.isInvalid()) {
                    acc + id.toLong()
                } else acc
            }
    }
}