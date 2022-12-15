package year2022.day14

import IProblem
import java.util.regex.Pattern
import kotlin.math.max
import kotlin.math.min

class Problem : IProblem {
    private val map = mutableMapOf<Point, Char>()
    private val topLeft: Point
    private val botRight: Point

    init {
        val pattern = Pattern.compile("(\\d+),(\\d+)")
        var topLeftX = START.x
        var topLeftY = START.y
        var botRightX = START.x
        var botRightY = START.y

        javaClass
            .getResourceAsStream("/2022/14.txt")!!
            .bufferedReader()
            .forEachLine {
                val matcher = pattern.matcher(it)
                if (!matcher.find()) return@forEachLine

                var x1 = matcher.group(1).toInt()
                var y1 = matcher.group(2).toInt()

                while (matcher.find()) {
                    val x2 = matcher.group(1).toInt()
                    val y2 = matcher.group(2).toInt()

                    val minX = min(x1, x2)
                    val maxX = max(x1, x2)
                    val minY = min(y1, y2)
                    val maxY = max(y1, y2)

                    topLeftX = min(minX, topLeftX)
                    topLeftY = min(minY, topLeftY)
                    botRightX = max(maxX, botRightX)
                    botRightY = max(maxY, botRightY)

                    if (x1 == x2) {
                        for (y in minY..maxY) {
                            map[Point(x2, y)] = ROCK
                        }
                    } else {
                        for (x in minX..maxX) {
                            map[Point(x, y2)] = ROCK
                        }
                    }

                    x1 = x2
                    y1 = y2
                }
            }

        topLeft = Point(topLeftX, topLeftY)
        botRight = Point(botRightX, botRightY)
    }

    private fun countWhile(predicate: () -> Boolean): Int {
        var count = 0

        while (predicate()) {
            count++
        }

        return count
    }

    private fun nextPos(map: Map<Point, Char>, curr: Point): Point {
        for (j in DROP_ORDER) {
            val next = Point(curr.x + j, curr.y + 1)

            if (!map.containsKey(next)) {
                return next
            }
        }

        return curr
    }

    private fun dropVoid(map: MutableMap<Point, Char>): Boolean {
        var curr = START

        while (curr.x in topLeft.x..botRight.x && curr.y <= botRight.y) {
            val next = nextPos(map, curr)

            if (curr == next) {
                map[next] = SAND
                return true
            }

            curr = next
        }

        return false
    }

    private fun dropGround(map: MutableMap<Point, Char>): Boolean {
        var curr = START

        while (curr.y != botRight.y + 1) {
            val next = nextPos(map, curr)

            if (curr == next) {
                map[next] = SAND
                return curr != START
            }

            curr = next
        }

        map[curr] = SAND
        return true
    }

    override fun part1(): Int {
        val copy = map.toMutableMap()
        return countWhile { dropVoid(copy) }
    }

    override fun part2(): Int {
        val copy = map.toMutableMap()
        return countWhile { dropGround(copy) } + 1
    }
}
