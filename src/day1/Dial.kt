package day1

data class Dial(val value : Int = 50) {
    companion object {
        fun create(value : Int) : Dial {
            return if (value > 99)
                Dial(0)
            else if (value < 0)
                Dial(99)
            else
                Dial(value)
        }
    }

    fun rotateDialRight() : Dial {
        return create(this.value + 1)
    }

    fun rotateDialLeft() : Dial {
        return create(this.value - 1)
    }
}