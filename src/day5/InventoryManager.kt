package day5

import java.io.File

class InventoryManager(val inventory: Set<String>, val items: List<String>) {

    companion object {
        fun parseInput(): InventoryManager {
            val set: MutableSet<String> = mutableSetOf()
            val items: MutableList<String> = mutableListOf()
            File("/Users/seppe.lenaerts/Documents/Repo/aoc/src/day5/input").forEachLine { it ->
                if (it.contains('-')) {
                    set.add(it)
                } else {
                    if (!it.isEmpty())
                        items.add(it)
                }
            }
            return InventoryManager(set, items)
        }
    }

    fun parseInventoryLines(): Int {
        println("inventory = ${inventory}")
        var mutItems = items
        return inventory.fold(0) {
            acc, string ->
            val split = string.split('-')
            println("acc = ${acc}")
            println("split = ${split}")
            val largest = split[1].toLong()
            val smallest = split[0].toLong()

            val copy = mutItems.toMutableList()
            val count = mutItems.count() {
                if (it.toLong() in smallest..largest) {
                    copy.remove(it)
                } else {
                    false
                }
            }
            mutItems = copy
            println("count = ${count}")
            acc + count
        }
    }
}