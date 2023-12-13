package year2023.day06

import IProblem
import java.util.regex.Pattern
import kotlin.math.*

class Problem : IProblem {
    private val time: Array<Double>
    private val distance: Array<Double>
    private val t: Double
    private val d: Double

    init {
        javaClass
            .getResourceAsStream("/2023/06.txt")!!
            .bufferedReader()
            .use { br ->
                val pattern = Pattern.compile("\\d+")
                val line0 = br.readLine()

                time = pattern
                    .matcher(line0)
                    .results()
                    .map { it.group().toDouble() }
                    .toArray { arrayOfNulls<Double>(it) }

                t = line0
                    .filter(Character::isDigit)
                    .toDouble()

                val line1 = br.readLine()

                distance = pattern
                    .matcher(line1)
                    .results()
                    .map { it.group().toDouble() }
                    .toArray { arrayOfNulls<Double>(it) }

                d = line1
                    .filter(Character::isDigit)
                    .toDouble()
            }
    }

    private fun quickmafs(t: Double, d: Double): Int {
        val x0 = (-t - sqrt(t * t - 4 * d)) / -2.0
        val x1 = (-t + sqrt(t * t - 4 * d)) / -2.0
        return abs(floor(min(x0, x1)) - ceil(max(x0, x1))).toInt() - 1
    }

    override fun part1(): Int {
        var res = 1

        for (i in time.indices) {
            res *= quickmafs(time[i], distance[i])
        }

        return res
    }

    override fun part2(): Int {
        return quickmafs(t, d)
    }
}
