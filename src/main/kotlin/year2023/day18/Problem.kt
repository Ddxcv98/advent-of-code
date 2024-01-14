package year2023.day18

import IProblem
import java.lang.Exception
import java.util.regex.Pattern

class Problem : IProblem {
    private val instructions = mutableListOf<Triple<Char, Long, String>>()

    init {
        val pattern = Pattern.compile("^([UDLR]) (\\d+) \\((.+)\\)$")

        javaClass
            .getResourceAsStream("/2023/18.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val match = pattern.matcher(line)

                if (!match.find()) {
                    throw Exception("bruh moment")
                }

                val dir = match.group(1)[0]
                val length = match.group(2).toLong()
                val colour = match.group(3)
                instructions.add(Triple(dir, length , colour))
            }
    }

    private fun volume(transform: (Triple<Char, Long, String>) -> Pair<Char, Long>): Long {
        var a = 0L
        var b = 0L
        var x0 = 0L
        var y0 = 0L

        for (instruction in instructions) {
            val (dir, length) = transform(instruction)

            val (x1, y1) = when (dir) {
                'U' -> Pair(x0, y0 - length)
                'D' -> Pair(x0, y0 + length)
                'L' -> Pair(x0 - length, y0)
                else -> Pair(x0 + length, y0)
            }

            a += x0 * y1 - y0 * x1
            b += length
            x0 = x1
            y0 = y1
        }

        return a / 2 + b / 2 + 1
    }

    override fun part1(): Long {
        return volume { (dir, length) -> Pair(dir, length) }
    }

    override fun part2(): Long {
        return volume { (_, _, colour) ->
            val dir = when(colour[6].digitToInt()) {
                0 -> 'R'
                1 -> 'D'
                2 -> 'L'
                else -> 'U'
            }

            val length = colour.substring(1, 6).toLong(16)

            Pair(dir, length)
        }
    }
}
