package day2

data class Id(val value: String) {
    fun toLong(): Long {
        return value.toLong()
    }

    fun isInvalid(): Boolean {
        if (value.length < 2) {
            return false
        }
        return IntRange(1, value.length / 2).map {
            value.chunked(it).toSet().size
        }.any { it == 1 }
    }
}
