package day1

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

class SafeUnlocker {
    var clicks = 0
    var dial : Dial = Dial()

    fun parseLine(input: String) {
        val amount : Int = input.drop(1).toInt()
        if (input.contains('R')) {
            for (i in 1..amount) {
                dial = dial.rotateDialRight()
                checkClick()
            }
        } else {
            for (i in 1..amount) {
                dial = dial.rotateDialLeft()
                checkClick()
            }
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