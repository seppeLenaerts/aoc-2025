package day3

import kotlin.math.pow

data class Bank(val batteries: List<Battery>) {

    companion object {
        fun createBank(input: String): Bank {
            return Bank(input.chunked(1).map { Battery(it.toInt()) })
        }
    }

    fun findHighestCombo() : Long {
        val findLargestLeftmostIndex = findLargestLeftmostIndex(12, batteries)
        return findLargestLeftmostIndex
    }

    fun findLargestLeftmostIndex(todo: Int, remainingBatteries: List<Battery>) : Long {
        if (todo == 0) {
            return 0
        }
        val joltages = remainingBatteries.map { it.joltage }
        var largest = 9
        while (largest > 0) {
            val largestIndex = joltages.indexOf(largest)
            if (largestIndex >= 0 && remainingBatteries.size - largestIndex >= todo) {
                return joltages[largestIndex] * (10.0.pow(todo.toDouble() - 1).toLong()) +
                        findLargestLeftmostIndex(
                            todo - 1,
                            remainingBatteries.subList(largestIndex + 1, remainingBatteries.size)
                        )
            }
            largest--
        }
        error("Shouldn't happen")
    }
}