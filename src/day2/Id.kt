package day2

data class Id(val value: String) {
    fun toLong() : Long {
        return value.toLong()
    }

    fun isInvalid() : Boolean {
        if (value.length % 2 == 1) {
            return false
        }
        val chunked = value.chunked(value.length / 2)
        return chunked[0] == chunked[1]
    }
}
