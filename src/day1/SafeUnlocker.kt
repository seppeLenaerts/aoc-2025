package day1

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

class SafeUnlocker {
    var clicks = 0
    var dial : Dial = Dial()

    fun parseLine(input: String) {
        val amount : Int = input.drop(1).toInt()
        rotate(amount, input.contains('R'))
    }

    fun rotate(amount : Int, right : Boolean) {
        for (i in 1..amount) {
            dial = if (right)
                dial.rotateDialRight()
            else
                dial.rotateDialLeft()
            checkClick()
        }
    }

    fun checkClick() {
        if (dial.value == 0)
            clicks++
    }

    fun parseInput() : Int {
        Path("/Users/seppe.lenaerts/Documents/Repo/aoc/src/day1/input").forEachLine {
            parseLine(it)
        }
        return clicks
    }
}