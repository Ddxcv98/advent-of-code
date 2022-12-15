package year2022.day08

import IProblem
import java.util.*
import kotlin.math.max

class Problem : IProblem {
    private val matrix = javaClass
        .getResource("/2022/08.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)
        .map { it.map(Char::digitToInt).toIntArray() }
        .toTypedArray()

    override fun part1(): Int {
        val n = matrix.size
        val seen = Array(n) { BooleanArray(n) }
        var count = 0

        for (i in 1 until n - 1) {
            var max = matrix[i][0]

            for (j in 1 until n - 1) {
                val height = matrix[i][j]

                if (height > max && !seen[i][j]) {
                    seen[i][j] = true
                    count++
                }

                max = max(height, max)
            }
        }

        for (j in 1 until n - 1) {
            var max = matrix[0][j]

            for (i in 1 until n - 1) {
                val height = matrix[i][j]

                if (height > max && !seen[i][j]) {
                    seen[i][j] = true
                    count++
                }

                max = max(height, max)
            }
        }

        for (i in 1 until n - 1) {
            var max = matrix[i][n - 1]

            for (j in n - 2 downTo 1) {
                val height = matrix[i][j]

                if (height > max && !seen[i][j]) {
                    seen[i][j] = true
                    count++
                }

                max = max(height, max)
            }
        }

        for (j in 1 until n - 1) {
            var max = matrix[n - 1][j]

            for (i in n - 2 downTo 1) {
                val height = matrix[i][j]

                if (height > max && !seen[i][j]) {
                    seen[i][j] = true
                    count++
                }

                max = max(height, max)
            }
        }

        return count + 4 * n - 4
    }

    override fun part2(): Int {
        val n = matrix.size
        val scenic = Array(n) { IntArray(n) { 1 } }
        var max = 0

        for (i in 1 until n - 1) {
            val map = TreeMap<Int, Int>()

            for (j in 1 until n - 1) {
                val height = matrix[i][j]

                if (height > matrix[i][j - 1]) {
                    val index = map.ceilingEntry(height)?.value ?: 0
                    scenic[i][j] *= j - index
                    max = max(scenic[i][j], max)
                }

                map[height] = j
            }
        }

        for (j in 1 until n - 1) {
            val map = TreeMap<Int, Int>()

            for (i in 1 until n - 1) {
                val height = matrix[i][j]

                if (height > matrix[i - 1][j]) {
                    val index = map.ceilingEntry(height)?.value ?: 0
                    scenic[i][j] *= i - index
                    max = max(scenic[i][j], max)
                }

                map[height] = i
            }
        }

        for (i in 1 until n - 1) {
            val map = TreeMap<Int, Int>()

            for (j in n - 2 downTo 1) {
                val height = matrix[i][j]

                if (height > matrix[i][j + 1]) {
                    val index = map.ceilingEntry(height)?.value ?: n
                    scenic[i][j] *= index - j - 1
                    max = max(scenic[i][j], max)
                }

                map[height] = j
            }
        }

        for (j in 1 until n - 1) {
            val map = TreeMap<Int, Int>()

            for (i in n - 2 downTo 1) {
                val height = matrix[i][j]

                if (height > matrix[i + 1][j]) {
                    val index = map.ceilingEntry(height)?.value ?: n
                    scenic[i][j] *= index - i - 1
                    max = max(scenic[i][j], max)
                }

                map[height] = i
            }
        }

        return max
    }
}
