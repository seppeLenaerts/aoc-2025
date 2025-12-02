package day2

data class Id(val value: String) {
    fun toInt() : Int {
        return value.toInt()
    }
}
