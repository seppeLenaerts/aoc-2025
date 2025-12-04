package day4

import java.io.File

data class Grid(val tiles: List<String>) {

    companion object {
        fun parse(): Grid {
            return Grid(File("D:\\Tools\\Repo\\aoc\\src\\day4\\input").readLines())
        }
    }

    fun markAccess() {
        IntRange(0, tiles.size - 1)
            .map {
                tiles[it].map { c ->
                    if (it == 0) {

                    }
                }
            }
    }

    fun checkLeft() {

    }

}
