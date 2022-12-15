package year2022.day10

import IProblem
import java.lang.StringBuilder
import kotlin.math.abs

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/10.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)

    private fun tick(): Sequence<Int> {
        return sequence {
            var x = 1

            for (line in lines) {
                yield(x)

                if (line != "noop") {
                    yield(x)
                    x += line.substring(5).toInt()
                }
            }
        }
    }

    override fun part1(): Int {
        var sum = 0

        for ((i, x) in tick().withIndex()) {
            val cycle = i + 1

            if (cycle % 40 == 20) {
                sum += cycle * x
            }
        }

        return sum
    }

    override fun part2(): String {
        val crt = Array(6) { BooleanArray(40) }
        val sb = StringBuilder()

        for ((i, x) in tick().withIndex()) {
            val row = i / 40
            val col = i % 40
            crt[row][col] = abs(col - x) < 2
        }

        for (row in crt) {
            for (pixel in row) {
                sb.append(if (pixel) '#' else '.')
            }

            sb.append('\n')
        }

        return sb.trim().toString()
    }
}
