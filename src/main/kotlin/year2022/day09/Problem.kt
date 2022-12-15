package year2022.day09

import IProblem
import java.util.regex.Pattern
import kotlin.math.absoluteValue

class Problem : IProblem {
    private val moves = mutableListOf<Move>()

    init {
        val pattern = Pattern.compile("([DLRU]) (\\d+)")

        javaClass
            .getResourceAsStream("/2022/09.txt")!!
            .bufferedReader()
            .forEachLine {
                val matcher = pattern.matcher(it)

                if (!matcher.find()) {
                    throw Exception("bruh moment")
                }

                moves.add(Move(matcher.group(1)[0], matcher.group(2).toInt()))
            }
    }

    private fun rope(k: Int): Int {
        val set = mutableSetOf(Pair(0, 0))
        val tail = Array(k) { IntArray(2) }

        for ((c, n) in moves) {
            for (i in 0 until n) {
                when (c) {
                    'D' -> tail[0][1]--
                    'L' -> tail[0][0]--
                    'R' -> tail[0][0]++
                    'U' -> tail[0][1]++
                }

                for (j in 1 until tail.size) {
                    val x = (tail[j - 1][0] - tail[j][0]).absoluteValue
                    val y = (tail[j - 1][1] - tail[j][1]).absoluteValue

                    if (x > 1 || y > 1) {
                        if (tail[j - 1][0] > tail[j][0]) {
                            tail[j][0]++
                        } else if (tail[j - 1][0] < tail[j][0]) {
                            tail[j][0]--
                        }

                        if (tail[j - 1][1] > tail[j][1]) {
                            tail[j][1]++
                        } else if (tail[j - 1][1] < tail[j][1]) {
                            tail[j][1]--
                        }

                        if (j == tail.lastIndex) {
                            set.add(Pair(tail[j][0], tail[j][1]))
                        }
                    }
                }
            }
        }

        return set.size
    }

    override fun part1(): Int {
        return rope(2)
    }

    override fun part2(): Int {
        return rope(10)
    }
}
