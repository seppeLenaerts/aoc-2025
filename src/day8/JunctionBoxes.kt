package day8

import java.io.File
import kotlin.math.absoluteValue

data class JunctionBoxes(val coords: List<Coords>, val circuits: MutableList<MutableSet<Coords>> = mutableListOf()) {

    companion object {
        fun create(): JunctionBoxes {
            return JunctionBoxes(File("D:\\Tools\\Repo\\aoc\\src\\day8\\input").readLines().map { line ->
                val split = line.split(',')
                Coords(split[0].toInt(), split[1].toInt(), split[2].toInt())
            })
        }
    }

    fun createConnections(): List<Connection> {
        val connections = coords.map {
            val pair = coords.filterNot { c -> it == c }.map { c ->
                Pair(c, c.distanceTo(it).absoluteValue)
            }.sortedBy { it.second }[0]
            Connection(it, pair.first, pair.second)
        }.sortedBy { it.distance }

        val list = mutableListOf<Connection>()
        connections.forEachIndexed { i, c -> if(i % 2 == 0) list.add(c) }
        return list
    }

    fun linkConnections(conns: List<Connection>) {

        coords.forEach { circuits.add(mutableSetOf(it))}

        conns.forEach {
            var stored = false

            if (circuits.isEmpty()) {
                circuits.add(mutableSetOf(it.c1, it.c2))
                stored = true
            }

            var toFilter: Coords = Coords(0,0,0)

            circuits.forEach { circuit ->
                if (circuit.contains(it.c1) && !stored) {
                    circuit.add(it.c2)
                    toFilter = it.c2
                    stored = true
                } else if (circuit.contains(it.c2) && !stored) {
                    circuit.add(it.c1)
                    toFilter = it.c1
                    stored = true
                }
            }

            val filter = circuits.filter { c -> c.contains(toFilter) && c.size == 1 }
            circuits.removeAll(filter)

            if (!stored) {
                circuits.add(mutableSetOf(it.c1, it.c2))
            }
        }
    }

    fun multBigThree(): Long {
        val subList = circuits.sortedBy { it.size }.reversed().subList(0, 3)
        return (subList[0].size * subList[1].size * subList[2].size).toLong()
    }
}
