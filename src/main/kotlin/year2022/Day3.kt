package year2022

import Problem

class Day3 : Problem {
    private val input = javaClass
        .getResource("/2022/3.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    private fun charValue(char: Char) =
        if (char.isUpperCase()) char.code - 38 else char.code - 96

    override fun part1() {
        var sum = 0

        for (line in input) {
            val half = line.length / 2
            val first = line.subSequence(0, half).toSet()
            val char = line.subSequence(half, line.length).find { first.contains(it) }!!
            sum += charValue(char)
        }

        println(sum)
    }

    override fun part2() {
        var sum = 0

        for (chunk in input.chunked(3)) {
            val map = HashMap<Char, Int>()

            for ((i, line) in chunk.withIndex()) {
                for (char in line) {
                    map[char] = map.getOrDefault(char, 0) or (1 shl i)
                }
            }

            val char = map.keys.find { map[it] == 7 }!!
            sum += charValue(char)
        }

        println(sum)
    }
}
