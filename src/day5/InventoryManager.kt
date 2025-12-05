package day5

import java.io.File

class InventoryManager(val inventory: Set<String>, val items: List<String>, val ranges: MutableList<LongRange> = mutableListOf()) {

    companion object {
        fun parseInput(): InventoryManager {
            val set: MutableSet<String> = mutableSetOf()
            val items: MutableList<String> = mutableListOf()
            File("/Users/seppe.lenaerts/Documents/Repo/aoc/src/day5/input").forEachLine {
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

    fun parseTotalIds() : Long {
        inventory.forEach {
            mergeOverlaps(it)
        }
        return ranges.sumOf { it.last - it.first + 1 }
    }

    fun mergeOverlaps(toMerge: String) {
        val split = toMerge.split('-')
        val newRange = LongRange(split[0].toLong(), split[1].toLong())

        val overlapping = ranges.filter { existing ->
            newRange.first <= existing.last && newRange.last >= existing.first
        }

        if (overlapping.isEmpty()) {
            ranges.add(newRange)
        } else {
            val minStart = minOf(newRange.first, overlapping.minOf { it.first })
            val maxEnd = maxOf(newRange.last, overlapping.maxOf { it.last })
            ranges.removeAll(overlapping)
            ranges.add(LongRange(minStart, maxEnd))
        }
    }

    fun parseInventoryLines(): Int {
        var mutItems = items
        return inventory.fold(0) {
            acc, string ->
            val split = string.split('-')
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
            acc + count
        }
    }
}