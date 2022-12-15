package year2022.day04

import IProblem
import java.lang.Exception
import java.util.regex.Pattern

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/04.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    private fun countWhen(predicate: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean): Int {
        val pattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)")
        var count = 0

        for (line in lines) {
            val matcher = pattern.matcher(line)

            if (!matcher.find()) {
                throw Exception("bruh moment")
            }

            val a = Pair(matcher.group(1).toInt(), matcher.group(2).toInt())
            val b = Pair(matcher.group(3).toInt(), matcher.group(4).toInt())

            if (predicate.invoke(a, b)) {
                count++
            }
        }

        return count
    }

    override fun part1(): Int {
        return countWhen { a, b -> a.first <= b.first && a.second >= b.second || b.first <= a.first && b.second >= a.second }
    }

    override fun part2(): Int {
        return countWhen { a, b -> a.second >= b.first && a.first <= b.second }
    }
}
