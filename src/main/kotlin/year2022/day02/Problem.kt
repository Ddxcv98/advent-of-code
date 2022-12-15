package year2022.day02

import IProblem

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/02.txt")!!
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

    override fun part1(): Int {
        var score = 0

        for (line in lines) {
            val theirs = line[0] - 'A'
            val ours = line[2] - 'X'

            score += ours + 1

            if (ours == theirs) {
                score += 3
            } else if ((ours - 1).mod(3) == theirs) {
                score += 6
            }
        }

        return score
    }

    override fun part2(): Int {
        var score = 0

        for (line in lines) {
            val play = line[0] - 'A'

            score += when (line[2]) {
                'X' -> (play - 1).mod(3) + 1
                'Y' -> play + 4
                'Z' -> (play + 1).mod(3) + 7
                else -> 0
            }
        }

        return score
    }
}
