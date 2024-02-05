package year2023.day22

import IProblem
import kotlin.math.max

class Problem : IProblem {
    private val blocks = mutableListOf<Pair<Triple<Int, Int, Int>, Triple<Int, Int, Int>>>()
    private val coords: Array<Array<IntArray>>
    private val support: Array<BooleanArray>
    private val depth: Int
    private val width: Int
    private val height: Int

    init {
        var d = 0
        var w = 0
        var h = 0

        javaClass
            .getResourceAsStream("/2023/22.txt")!!
            .bufferedReader()
            .forEachLine {
                val split0 = it.split('~')
                val split1 = split0[0].split(',')
                val split2 = split0[1].split(',')
                val start = Triple(split1[0].toInt(), split1[1].toInt(), split1[2].toInt())
                val end = Triple(split2[0].toInt(), split2[1].toInt(), split2[2].toInt())

                if (end.first > d) {
                    d = end.first
                }

                if (end.second > w) {
                    w = end.second
                }

                if (end.third > h) {
                    h = end.third
                }

                blocks.add(Pair(start, end))
            }

        blocks.sortBy { it.first.third }
        depth = d + 1
        width = w + 1
        height = h + 1
        coords = Array(depth) { Array(width) { IntArray(height) } }
        support = Array(blocks.size + 1) { BooleanArray(blocks.size + 1) }

        dropItLikeItsHot()
        mapSupport()
    }

    private fun dropItLikeItsHot() {
        val elevator = Array(depth) { IntArray(width) }
        var i = 1

        for ((start, end) in blocks) {
            val (x0, y0, z0) = start
            val (x1, y1, z1) = end
            var h = 0

            for (x in x0..x1) {
                h = max(elevator[x][y0], h)
            }

            for (y in y0 + 1..y1) {
                h = max(elevator[x0][y], h)
            }

            for (x in x0..x1) {
                coords[x][y0][h] = i
                elevator[x][y0] = h + 1
            }

            for (y in y0 + 1..y1) {
                coords[x0][y][h] = i
                elevator[x0][y] = h + 1
            }

            for (z in z0 + 1..z1) {
                coords[x0][y0][++h] = i
            }

            elevator[x0][y0] = h + 1
            i++
        }
    }

    private fun mapSupport() {
        for (z in 0 until height - 1) {
            for (x in 0 until depth) {
                for (y in 0 until width) {
                    val i = coords[x][y][z]
                    val j = coords[x][y][z + 1]

                    if (i != 0 && j != 0 && i != j) {
                        support[i][j] = true
                    }
                }
            }
        }
    }

    private fun hasSupport(support: Array<BooleanArray>, i: Int, j: Int): Boolean {
        for (k in support.indices) {
            if (i != k && support[k][j]) {
                return true
            }
        }

        return false
    }

    private fun yeet(support: Array<BooleanArray>, i: Int): Boolean {
        for (j in support.indices)  {
            if (support[i][j] && !hasSupport(support, i, j)) {
                return false
            }
        }

        return true
    }

    private fun hasSupport(support: Array<BooleanArray>, yeeted: BooleanArray, i: Int): Boolean {
        for (j in support.indices) {
            if (support[j][i] && !yeeted[j]) {
                return true
            }
        }

        return false
    }

    private fun dfs(coords: Array<Array<IntArray>>, support: Array<BooleanArray>, yeeted: BooleanArray, i: Int): Int {
        var n = 0

        yeeted[i] = true

        for (j in support.indices) {
            if (support[i][j] && !hasSupport(support, yeeted, j)) {
                n += dfs(coords, support, yeeted, j) + 1
            }
        }

        return n
    }

    override fun part1(): Int {
        var count = 0

        for (i in 1..blocks.size) {
            if (yeet(support, i)) {
                count++
            }
        }

        return count
    }

    override fun part2(): Int {
        var sum = 0

        for (i in 1..blocks.size) {
            sum += dfs(coords, support, BooleanArray(blocks.size + 1), i)
        }

        return sum
    }
}
