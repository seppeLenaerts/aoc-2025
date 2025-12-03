import day1.SafeUnlocker
import day2.ProductCode
import day3.JoltageMeter

fun main() {
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

fun day3() {
    val joltageMeter = JoltageMeter()
    println(joltageMeter.measureTotal())
}