package year2023.day16

import IProblem
import kotlin.math.max

class Problem : IProblem {
    val map = javaClass
        .getResourceAsStream("/2023/16.txt")!!
        .bufferedReader()
        .lines()
        .map(String::toCharArray)
        .toList()
        .toTypedArray()

    private val n = map.size

    private fun beamDeezNuts(start: Triple<Int, Int, Int>): Int {
        val matrix = Array(n) { IntArray(n) }
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        var count = 0

        queue.add(start)

        while (queue.isNotEmpty()) {
            val (x, y, mask) = queue.removeFirst()

            if (x !in 0..<n || y !in 0..<n || matrix[y][x].and(mask) == mask) {
                continue
            }

            if (matrix[y][x] == 0) {
                count++
            }

            matrix[y][x] = matrix[y][x].or(mask)

            when (map[y][x]) {
                '-' -> {
                    queue.add(Triple(x - 1, y, LEFT))
                    queue.add(Triple(x + 1, y, RIGHT))
                }
                '.' -> {
                    when (mask) {
                        LEFT -> queue.add(Triple(x - 1, y, mask))
                        UP -> queue.add(Triple(x, y - 1, mask))
                        RIGHT -> queue.add(Triple(x + 1, y, mask))
                        DOWN -> queue.add(Triple(x, y + 1, mask))
                    }
                }
                '/' -> {
                    when (mask) {
                        LEFT -> queue.add(Triple(x, y + 1, DOWN))
                        UP -> queue.add(Triple(x + 1, y, RIGHT))
                        RIGHT -> queue.add(Triple(x, y - 1, UP))
                        DOWN -> queue.add(Triple(x - 1, y, LEFT))
                    }
                }
                '\\' -> {
                    when (mask) {
                        LEFT -> queue.add(Triple(x, y - 1, UP))
                        UP ->  queue.add(Triple(x - 1, y, LEFT))
                        RIGHT -> queue.add(Triple(x, y + 1, DOWN))
                        DOWN -> queue.add(Triple(x + 1, y, RIGHT))
                    }
                }
                '|' -> {
                    queue.add(Triple(x, y - 1, UP))
                    queue.add(Triple(x, y + 1, DOWN))
                }
            }
        }

        return count
    }

    override fun part1(): Int {
        return beamDeezNuts(Triple(0, 0, RIGHT))
    }

    override fun part2(): Int {
        var max = 0

        for (i in 0 until n) {
            max = max(beamDeezNuts(Triple(0, i, RIGHT)), max)
            max = max(beamDeezNuts(Triple(i, 0, DOWN)), max)
            max = max(beamDeezNuts(Triple(n - 1, i, LEFT)), max)
            max = max(beamDeezNuts(Triple(i, n - 1, UP)), max)
        }

        return max
    }
}
