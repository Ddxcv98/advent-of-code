package year2022

import Problem
import java.util.PriorityQueue
import kotlin.math.max

class Day1 : Problem {
    private val input = javaClass
        .getResource("/2022/1.txt")!!
        .readText()
        .lines()

    override fun part1() {
        var max = 0
        var current = 0

        for (line in input) {
            if (line.isEmpty()) {
                max = max(current, max)
                current = 0
            } else {
                current += line.toInt()
            }

        }

        println(max)
    }

    override fun part2() {
        val queue = PriorityQueue<Int>()
        var current = 0

        for (line in input) {
            if (line.isEmpty()) {
                queue.add(-current)
                current = 0
            } else {
                current += line.toInt()
            }
        }

        println(-queue.poll() - queue.poll() - queue.poll())
    }
}
