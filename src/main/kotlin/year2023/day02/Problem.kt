package year2023.day02

import IProblem

class Problem : IProblem {
    private val games = mutableListOf<Game>()

    init {
        javaClass
            .getResourceAsStream("/2023/02.txt")!!
            .bufferedReader()
            .forEachLine(::parseGame)
    }

    private fun parseGame(s: String) {
        val split = s.split(": ")

        val id = split[0]
            .filter { it.isDigit() }
            .toInt()

        val game = Game(id)
        parseSets(game, split[1])
        games.add(game)
    }

    private fun parseSets(game: Game, s: String) {
        val split = s.split("; ")

        for (t in split) {
            val set = mutableListOf<Pair<String, Int>>()
            parseCubes(set, t)
            game.sets.add(set)
        }
    }

    private fun parseCubes(set: MutableCollection<Pair<String, Int>>, s: String) {
        val split = s.split(", ")

        for (t in split) {
            val (n, color) = t.split(' ')
            set.add(color to n.toInt())
        }
    }

    private fun isValid(game: Game, max: Map<String, Int>): Boolean {
        for (set in game.sets) {
            for (cube in set) {
                if (cube.second > max[cube.first]!!) {
                    return false
                }
            }
        }

        return true
    }

    override fun part1(): Int {
        val max = mutableMapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        var sum = 0

        for (game in games) {
            if (isValid(game, max)) {
                sum += game.id
            }
        }

        return sum
    }

    override fun part2(): Int {
        var sum = 0

        for (game in games) {
            val max = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )

            for (set in game.sets) {
                for (cube in set) {
                    if (cube.second > max[cube.first]!!) {
                        max[cube.first] = cube.second
                    }
                }
            }

            sum += max.values.fold(1) { acc, x -> acc * x }
        }

        return sum
    }
}
