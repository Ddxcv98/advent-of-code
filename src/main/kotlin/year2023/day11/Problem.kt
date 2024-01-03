package year2023.day11

import IProblem
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Problem : IProblem {
    private val size: Int
    private val space: Array<IntArray>
    private val emptyRows: BooleanArray
    private val emptyCols: BooleanArray
    private val galaxies = mutableListOf<Pair<Int, Int>>()

    init {
        val lines = javaClass
            .getResourceAsStream("/2023/11.txt")!!
            .bufferedReader()
            .lines()
            .map(String::toCharArray)
            .toList()

        size = lines.size
        space = Array(size) { IntArray(size) { -1 } }
        emptyRows = BooleanArray(size) { true }
        emptyCols = BooleanArray(size) { true }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (lines[i][j] == '#') {
                    space[i][j] = galaxies.size
                    emptyRows[i] = false
                    emptyCols[j] = false
                    galaxies.add(Pair(j, i))
                }
            }
        }
    }

    private fun distSum(n: Int): Long {
        var sum = 0L

        for (i in galaxies.indices) {
            for (j in i + 1 until galaxies.size) {
                val (x0, y0) = galaxies[i]
                val (x1, y1) = galaxies[j]
                var r = 0
                var c = 0

                for (k in min(x0, x1) until max(x0, x1)) {
                    if (emptyCols[k]) {
                        c++
                    }
                }

                for (k in min(y0, y1) until max(y0, y1)) {
                    if (emptyRows[k]) {
                        r++
                    }
                }

                sum += c * (n - 1) + abs(x0 - x1) + r * (n - 1) + abs(y0 - y1)
            }
        }

        return sum
    }

    override fun part1(): Long {
        return distSum(2)
    }

    override fun part2(): Long {
        return distSum(1_000_000)
    }
}
