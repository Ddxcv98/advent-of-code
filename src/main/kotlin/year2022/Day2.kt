package year2022

import Problem

class Day2 : Problem {
    private val input = javaClass
        .getResource("/2022/2.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    override fun part1() {
        var score = 0

        for (line in input) {
            val theirs = line[0] - 'A'
            val ours = line[2] - 'X'

            score += ours + 1

            if (ours == theirs) {
                score += 3
            } else if ((ours - 1).mod(3) == theirs) {
                score += 6
            }
        }

        println(score)
    }

    override fun part2() {
        var score = 0

        for (line in input) {
            val play = line[0] - 'A'

            score += when (line[2]) {
                'X' -> (play - 1).mod(3) + 1
                'Y' -> play + 4
                'Z' -> (play + 1).mod(3) + 7
                else -> 0
            }
        }

        println(score)
    }
}
