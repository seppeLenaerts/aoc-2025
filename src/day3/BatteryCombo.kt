package day3

data class BatteryCombo(val batteries: List<Battery>) {
    fun totalJoltage(): Long {
        return batteries.fold(0) { acc, battery -> (acc * 10) + battery.joltage }
    }
}