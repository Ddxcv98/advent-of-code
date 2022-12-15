package year2022.day06

import IProblem

class Problem : IProblem {
    private val chars = javaClass
        .getResource("/2022/06.txt")!!
        .readText()
        .trim()
        .toCharArray()

    private fun nonRepeatingSubstring(k: Int): Int {
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

        return j + 1
    }

    override fun part1(): Int {
        return nonRepeatingSubstring(4)
    }

    override fun part2(): Int {
        return nonRepeatingSubstring(14)
    }
}
