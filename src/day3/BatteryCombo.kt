package day3

data class BatteryCombo(val battery1: Battery, val battery2: Battery) {
    fun totalJoltage(): Int {
        return battery1.joltage * 10 + battery2.joltage
    }
}