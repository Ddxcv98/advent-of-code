package year2022.day19

import IProblem
import java.util.regex.Pattern
import kotlin.math.max
import kotlin.math.min

class Problem : IProblem {
    private val blueprints = mutableListOf<Array<IntArray>>()

    init {
        val pattern = Pattern.compile("\\d+")

        javaClass
            .getResourceAsStream("/2022/19.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val matches = pattern
                    .matcher(line)
                    .results()
                    .map { it.group().toInt() }
                    .toList()

                blueprints.add(
                    arrayOf(
                        intArrayOf(matches[1], 0, 0, 0),
                        intArrayOf(matches[2], 0, 0, 0),
                        intArrayOf(matches[3], matches[4], 0, 0),
                        intArrayOf(matches[5], 0, matches[6], 0)
                    )
                )
            }
    }

    private fun geodes(best: Int, blueprint: Array<IntArray>, robots: IntArray, money: IntArray, time: Int): Int {
        if (time == 0) {
            return max(best, money[GEODE])
        }

        if (money[GEODE] + time * robots[GEODE] + time * (time - 1) / 2 <= best) {
            return best
        }

        var n = max(best, money[GEODE] + time * robots[GEODE])

        for (i in 0 until SIZE) {
            val cost = blueprint[i]

            if ((0 until SIZE).any { cost[it] != 0 && robots[it] == 0 }) {
                continue
            }

            val mins = (0 until SIZE).maxOf {
                if (money[it] >= cost[it]) {
                    1
                } else {
                    (cost[it] - money[it] + 2 * robots[it] - 1) / robots[it]
                }
            }

            if (time < mins) {
                continue
            }

            val updatedRobots = robots.copyOf()
            val updatedMoney = IntArray(SIZE) { money[it] + mins * robots[it] - cost[it] }
            updatedRobots[i]++
            n = geodes(n, blueprint, updatedRobots, updatedMoney, time - mins)
        }

        return n
    }

    override fun part1(): Int {
        val robots = intArrayOf(1, 0, 0, 0)
        val money = intArrayOf(0, 0, 0, 0)

        return blueprints
            .indices
            .sumOf { (it + 1) * geodes(0, blueprints[it], robots, money, 24) }
    }

    override fun part2(): Int {
        val robots = intArrayOf(1, 0, 0, 0)
        val money = intArrayOf(0, 0, 0, 0)

        return (0 until min(3, blueprints.size))
            .fold(1) { acc, i -> acc * geodes(0, blueprints[i], robots, money, 32) }
    }
}
