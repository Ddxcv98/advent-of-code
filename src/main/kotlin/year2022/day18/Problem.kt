package year2022.day18

import IProblem

class Problem : IProblem {
    private val points = javaClass
        .getResource("/2022/18.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)
        .map {
            val split = it.split(',').map(String::toInt)
            Point(split[0], split[1], split[2])
        }

    private val depth = points.maxOf(Point::x) + 2
    private val height = points.maxOf(Point::y) + 2
    private val width = points.maxOf(Point::z) + 2
    private val map: Array<Array<BooleanArray>> = Array(depth) { Array(height) { BooleanArray(width) } }

    init {
        for ((x, y, z) in points) {
            map[x][y][z] = true
        }
    }

    private fun floodFill(visited: Array<Array<BooleanArray>>, i: Int, j: Int, k: Int): Int {
        if (visited[i][j][k]) {
            return 0
        }

        visited[i][j][k] = true

        var n = 0

        if (i != 0) {
            n += if (map[i - 1][j][k]) {
                1
            } else {
                floodFill(visited, i - 1, j, k)
            }
        }

        if (i != depth - 1) {
            n += if (map[i + 1][j][k]) {
                1
            } else {
                floodFill(visited, i + 1, j, k)
            }
        }

        if (j != 0) {
            n += if (map[i][j - 1][k]) {
                1
            } else {
                floodFill(visited, i, j - 1, k)
            }
        }

        if (j != height - 1) {
            n += if (map[i][j + 1][k]) {
                1
            } else {
                floodFill(visited, i, j + 1, k)
            }
        }

        if (k != 0) {
            n += if (map[i][j][k - 1]) {
                1
            } else {
                floodFill(visited, i, j, k - 1)
            }
        }

        if (k != width - 1) {
            n += if (map[i][j][k + 1]) {
                1
            } else {
                floodFill(visited, i, j, k + 1)
            }
        }

        return n
    }

    override fun part1(): Int {
        var count = 0

        for ((x, y, z) in points) {
            if (!map[x - 1][y][z]) {
                count++
            }

            if (!map[x + 1][y][z]) {
                count++
            }

            if (!map[x][y - 1][z]) {
                count++
            }

            if (!map[x][y + 1][z]) {
                count++
            }

            if (!map[x][y][z - 1]) {
                count++
            }

            if (!map[x][y][z + 1]) {
                count++
            }
        }

        return count
    }

    override fun part2(): Int {
        val visited = Array(depth) { Array(height) { BooleanArray(width) } }
        return floodFill(visited, 0, 0, 0)
    }
}
