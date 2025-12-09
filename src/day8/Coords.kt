package day8

import kotlin.math.pow
import kotlin.math.sqrt

data class Coords(val x: Int, val y: Int, val z: Int) {
    fun distanceTo(other: Coords): Double {
        val a = (x - other.x).toDouble()
        val b = (y - other.y).toDouble()
        val c = (z - other.z).toDouble()

        val total = a.pow(2) + b.pow(2) + c.pow(2)

        return sqrt(total)
    }
}
