package year2022

import Problem
import kotlin.math.abs

class Day10 : Problem {
    private val input = javaClass
        .getResource("/2022/10.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    private fun tick(): Sequence<Int> {
        return sequence {
            var x = 1

            for (line in input) {
                yield(x)

                if (line != "noop") {
                    yield(x)
                    x += line.substring(5).toInt()
                }
            }
        }
    }

    override fun part1() {
        var sum = 0

        for ((i, x) in tick().withIndex()) {
            val cycle = i + 1

            if (cycle % 40 == 20) {
                sum += cycle * x
            }
        }

        println(sum)
    }

    override fun part2() {
        val crt = Array(6) { BooleanArray(40) }

        for ((i, x) in tick().withIndex()) {
            val row = i / 40
            val col = i % 40
            crt[row][col] = abs(col - x) < 2
        }

        for (row in crt) {
            println(row.map { if (it) '#' else '.' }.joinToString(""))
        }
    }
}
