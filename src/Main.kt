import day1.SafeUnlocker
import day2.ProductCode
import day3.JoltageMeter
import day4.Grid
import day5.InventoryManager
import day6.Calculator

fun main() {
    day6()
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

fun day4() {
    println(Grid.parse().howMany())
}

fun day5() {
    println(InventoryManager.parseInput().parseTotalIds())
}

fun day6() {
    val calculator = Calculator.create()
    println(calculator.solve())
}