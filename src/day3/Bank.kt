package day3

data class Bank(val batteries: List<Battery>) {

    companion object {
        fun createBank(input: String): Bank {
            return Bank(input.chunked(1).map { Battery(it.toInt()) })
        }
    }

    fun findHighestCombo() : Long {
        return IntRange(0, batteries.size - 12)
            .maxOf {
                n -> BatteryCombo(batteries.subList(n, n+11)).totalJoltage()
            }

        batteries.map { it.joltage }.find {  }
    }

}