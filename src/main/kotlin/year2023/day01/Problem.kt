package year2023.day01

import IProblem
import java.util.NoSuchElementException

class Problem : IProblem {
    private val map = mapOf(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    private val lines = javaClass
        .getResource("/2023/01.txt")!!
        .readText()
        .lines()
        .filter(String::isNotBlank)

    private fun firstMatch(keys: Iterable<String>, s: String): Int {
        for (i in s.indices) {
            for (key in keys) {
                if (s.regionMatches(i, key, 0, key.length)) {
                    return map[key]!!
                }
            }
        }

        throw NoSuchElementException("no key found in $s")
    }

    private fun lastMatch(keys: Iterable<String>, s: String): Int {
        for (i in s.indices.reversed()) {
            for (key in keys) {
                if (s.regionMatches(i, key, 0, key.length)) {
                    return map[key]!!
                }
            }
        }

        throw NoSuchElementException("no key found in $s")
    }

    private fun calculate(keys: Iterable<String>): Int {
        var sum = 0

        for (line in lines) {
            val x = firstMatch(keys, line)
            val y = lastMatch(keys, line)
            sum += x * 10 + y
        }

        return sum
    }

    override fun part1(): Int {
        val keys = map.keys.filter { it.length == 1 }
        return calculate(keys)
    }

    override fun part2(): Int {
        val keys = map.keys
        return calculate(keys)
    }
}
