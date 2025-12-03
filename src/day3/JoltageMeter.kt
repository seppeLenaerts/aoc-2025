package day3

import java.io.File

class JoltageMeter {

    fun measureTotal(): Int {
        val banks = File("D:\\Tools\\Repo\\aoc\\src\\day3\\input").readLines().map { Bank.createBank(it) }
        return banks.map { it.findHighestCombo() }.sumOf { it }
    }

}