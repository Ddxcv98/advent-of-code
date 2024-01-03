package year2023.day10

import IProblem
import java.lang.Exception
import kotlin.math.abs

class Problem : IProblem {
    private val matrix: Array<CharArray>
    private val n: Int
    private val m: Int
    private val start: Pair<Int, Int>

    init {
        val lines = javaClass
            .getResourceAsStream("/2023/10.txt")!!
            .bufferedReader()
            .lines()
            .toList()

        matrix = Array(lines.size) { i -> lines[i].toCharArray() }
        n = matrix.size
        m = matrix[0].size
        start = findStart()
    }

    private fun findStart(): Pair<Int, Int> {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (matrix[i][j] == 'S') {
                    return Pair(j, i)
                }
            }
        }

        throw Exception("bruh moment")
    }

    private fun walk(prev: Pair<Int, Int>, curr: Pair<Int, Int>): Pair<Int, Int> {
        val (px, py) = prev
        val (cx, cy) = curr

        return when (matrix[cy][cx]) {
            '-' -> if (px < cx) Pair(cx + 1, cy) else Pair(cx - 1, cy)
            '7' -> if (px < cx) Pair(cx, cy + 1) else Pair(cx - 1, cy)
            'F' -> if (px > cx) Pair(cx, cy + 1) else Pair(cx + 1, cy)
            'J' -> if (px < cx) Pair(cx, cy - 1) else Pair(cx - 1, cy)
            'L' -> if (px > cx) Pair(cx, cy - 1) else Pair(cx + 1, cy)
            '|' -> if (py < cy) Pair(cx, cy + 1) else Pair(cx, cy - 1)
            'S' -> {
                if (cx > 0 && matrix[cy][cx - 1] in "-FL") {
                    return Pair(cx - 1, cy)
                }

                if (cy > 0 && matrix[cy - 1][cx] in "7F|") {
                    return Pair(cx, cy - 1)
                }

                if (cx < m - 1 && matrix[cy][cx + 1] in "-7J") {
                    return Pair(cx + 1, cy)
                }

                return Pair(cx, cy + 1)
            }
            else -> throw Exception("bruh moment")
        }
    }

    override fun part1(): Int {
        var prev = start
        var curr = start
        var steps = 0

        do {
            val next = walk(prev, curr)
            prev = curr
            curr = next
            steps++
        } while (curr != start)

        return steps / 2
    }

    override fun part2(): Int {
        var prev = start
        var curr = start
        var a = 0
        var b = 0

        do {
            val next = walk(prev, curr)
            prev = curr
            curr = next
            a += prev.first * curr.second - curr.first * prev.second
            b++
        } while (curr != start)

        return abs(a) / 2 - b / 2 + 1
    }
}
