package year2022.day03

import IProblem

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/03.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    private fun charValue(char: Char) =
        if (char.isUpperCase()) char.code - 38 else char.code - 96

    override fun part1(): Int {
        var sum = 0

        for (line in lines) {
            val half = line.length / 2
            val first = line.subSequence(0, half).toSet()
            val char = line.subSequence(half, line.length).find { first.contains(it) }!!
            sum += charValue(char)
        }

        return sum
    }

    override fun part2(): Int {
        var sum = 0

        for (chunk in lines.chunked(3)) {
            val map = mutableMapOf<Char, Int>()

            for ((i, line) in chunk.withIndex()) {
                for (char in line) {
                    map[char] = map.getOrDefault(char, 0) or (1 shl i)
                }
            }

            val char = map.keys.find { map[it] == 7 }!!
            sum += charValue(char)
        }

        return sum
    }
}
