package year2022.day01

import IProblem
import java.util.PriorityQueue
import kotlin.math.max

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/01.txt")!!
        .readText()
        .lines()

    override fun part1(): Int {
        var max = 0
        var current = 0

        for (line in lines) {
            if (line.isEmpty()) {
                max = max(current, max)
                current = 0
            } else {
                current += line.toInt()
            }

        }

        return max
    }

    override fun part2(): Int {
        val queue = PriorityQueue<Int>()
        var current = 0

        for (line in lines) {
            if (line.isEmpty()) {
                queue.add(-current)
                current = 0
            } else {
                current += line.toInt()
            }
        }

        return -queue.poll() - queue.poll() - queue.poll()
    }
}
