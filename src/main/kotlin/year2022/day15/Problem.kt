package year2022.day15

import IProblem
import java.util.regex.Pattern
import kotlin.math.abs

class Problem(private val y: Int, private val bounds: Int) : IProblem {
    private val beacons = mutableSetOf<Point>()
    private val distances = mutableMapOf<Point, Int>()

    init {
        val pattern = Pattern.compile("-?\\d+")

        javaClass
            .getResourceAsStream("/2022/15.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val matches = pattern
                    .matcher(line)
                    .results()
                    .map { it.group().toInt() }
                    .toList()

                val sensor = Point(matches[0], matches[1])
                val beacon = Point(matches[2], matches[3])

                beacons.add(beacon)
                distances[sensor] = sensor.taxicabDistance(beacon)
            }
    }

    private fun isCovered(point: Point): Boolean {
        for ((sensor, distance) in distances) {
            if (point.taxicabDistance(sensor) <= distance) {
                return true
            }
        }

        return false
    }

    private fun findNonCovered(): Point? {
        for ((sensor, distance) in distances.mapValues { it.value + 1 }) {
            for (i in -distance..distance) {
                for (j in arrayOf(-distance + abs(i), distance - abs(i))) {
                    val y = sensor.y + j
                    val x = sensor.x + i

                    if (x < 0 || x > bounds || y < 0 || y > bounds) {
                        continue
                    }

                    val point = Point(x, y)

                    if (!isCovered(point)) {
                        return point
                    }
                }
            }
        }

        return null
    }

    override fun part1(): Int {
        val l = distances.minOf { it.key.x - it.value }
        val r = distances.maxOf { it.key.x + it.value }
        var count = 0

        for (x in l..r) {
            val point = Point(x, y)

            if (!beacons.contains(point) && isCovered(point)) {
                count++
            }
        }

        return count
    }

    override fun part2(): ULong {
        val point = findNonCovered()!!
        return point.x.toULong() * 4000000UL + point.y.toULong()
    }
}
