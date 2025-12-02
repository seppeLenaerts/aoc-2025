import day1.SafeUnlocker
import day2.ProductCode

fun main() {
    // Day 1
    //day1()

    // Day 2
    day2()
}

fun day1() {
    val safeUnlocker = SafeUnlocker()
    println(safeUnlocker.parseInput())
}

fun day2() {
    val mismatches = ProductCode.scanInput().map { it.validate() }.reduce { sum, mismatched -> sum + mismatched }
    print(mismatches)
}