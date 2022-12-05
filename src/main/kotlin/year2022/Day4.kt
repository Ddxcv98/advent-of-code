package year2022

import Problem
import java.lang.Exception
import java.util.regex.Pattern

class Day4 : Problem {
    private val input = javaClass
        .getResource("/2022/4.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    private fun countWhen(predicate: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean) {
        val pattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)")
        var count = 0

        for (line in input) {
            val matcher = pattern.matcher(line)

            if (!matcher.find()) {
                throw Exception("Could not match pattern")
            }

            val a = Pair(matcher.group(1).toInt(), matcher.group(2).toInt())
            val b = Pair(matcher.group(3).toInt(), matcher.group(4).toInt())

            if (predicate.invoke(a, b)) {
                count++
            }
        }

        println(count)
    }

    override fun part1() {
        countWhen { a, b -> a.first <= b.first && a.second >= b.second || b.first <= a.first && b.second >= a.second }
    }

    override fun part2() {
        countWhen { a, b -> a.second >= b.first && a.first <= b.second }
    }
}
