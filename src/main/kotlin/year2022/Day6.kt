package year2022

import Problem

class Day6 : Problem {
    private val input = javaClass
        .getResource("/2022/6.txt")!!
        .readText()
        .lines()
        .first()

    private fun nonRepeatingSubstring(k: Int) {
        val chars = input.toCharArray()
        val map = IntArray(256) { -1 }
        var i = 0
        var j = 0

        while (j < chars.size) {
            val char = chars[j].code
            val index = map[char]

            if (i <= index) {
                i = index + 1
            }

            if (j - i + 1 == k) {
                break
            }

            map[char] = j++
        }

        println(j + 1)
    }

    override fun part1() {
        nonRepeatingSubstring(4)
    }

    override fun part2() {
        nonRepeatingSubstring(14)
    }
}
