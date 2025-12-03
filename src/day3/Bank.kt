package day3

data class Bank(val batteries: List<Battery>) {

    companion object {
        fun createBank(input: String): Bank {
            return Bank(input.chunked(1).map { Battery(it.toInt()) })
        }
    }

    fun findHighestCombo() : Int {
        return IntRange(1, batteries.size - 1)
            .maxOf {
                n -> batteries.subList(n, batteries.size).maxOf {
                    b -> BatteryCombo(batteries[n - 1], b).totalJoltage()
                }
            }
    }

}