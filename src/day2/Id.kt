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
            val chunked = value.chunked(it)
            val matcher = chunked[0]
            chunked.fold(0) { acc, string -> if (matcher == string) acc + 1 else acc } == chunked.size
        }.any { it }
    }
}
