package year2023.day23

import IProblem
import kotlin.math.max

class Problem : IProblem {
    private val matrix = javaClass
        .getResourceAsStream("/2023/23.txt")!!
        .bufferedReader()
        .lines()
        .map(String::toCharArray)
        .toList()
        .toTypedArray()

    private val n = matrix.size

    private fun adjacent(x: Int, y: Int, force: Boolean) = sequence {
        if (force || matrix[y][x] == '.') {
            yield(Pair(x - 1, y))
            yield(Pair(x + 1, y))
            yield(Pair(x, y - 1))
            yield(Pair(x, y + 1))
        } else {
            when (matrix[y][x]) {
                '^' -> yield(Pair(x, y - 1))
                '>' -> yield(Pair(x + 1, y))
                'v' -> yield(Pair(x, y + 1))
                '<' -> yield(Pair(x - 1, y))
            }
        }
    }

    private fun walk(x: Int, y: Int, force: Boolean = false) = adjacent(x, y, force)
        .filter { (x, y) -> x in 0..<n && y in 0..<n && matrix[y][x] != '#' }

    private fun dfs(visited: Array<BooleanArray>, x0: Int, y0: Int, w: Int): Int {
        if (y0 == n - 1) {
            return w
        }

        var steps = 0

        visited[y0][x0] = true

        for ((x1, y1) in walk(x0, y0)) {
            if (!visited[y1][x1]) {
                steps = max(steps, dfs(visited, x1, y1, w + 1))
            }
        }

        visited[y0][x0] = false

        return steps
    }

    private fun findIntersections(intersections: Array<IntArray>): Int {
        var k = 0

        intersections[0][1] = k++

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (matrix[i][j] == '.' && walk(j, i, true).count() > 2) {
                    intersections[i][j] = k++
                }
            }
        }

        intersections[n - 1][n - 2] = k++
        return k
    }

    private fun findEdges(graph: Array<IntArray>, intersections: Array<IntArray>, u: Int, x: Int, y: Int) {
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        val visited = Array(n) { BooleanArray(n) }

        queue.add(Triple(x, y, 0))
        visited[y][x] = true

        while (queue.isNotEmpty()) {
            val (x0, y0, steps) = queue.removeFirst()
            val v = intersections[y0][x0]

            if (v != -1) {
                graph[u][v] = steps

                if (u != v) {
                    continue
                }
            }

            for ((x1, y1) in walk(x0, y0, true)) {
                if (!visited[y1][x1]) {
                    queue.addLast(Triple(x1, y1, steps + 1))
                    visited[y1][x1] = true
                }
            }
        }
    }

    private fun longestPath(graph: Array<IntArray>, visited: BooleanArray, u: Int, w0: Int): Int {
        if (u == graph.size - 1) {
            return w0
        }

        var steps = 0

        visited[u] = true

        for (v in graph[u].indices) {
            val w1 = graph[u][v]

            if (w1 > 0 && !visited[v]) {
                steps = max(steps, longestPath(graph, visited, v, w0 + w1))
            }
        }

        visited[u] = false

        return steps
    }

    override fun part1(): Int {
        val visited = Array(n) { BooleanArray(n) }
        return dfs(visited, 1, 0, 0)
    }

    override fun part2(): Int {
        val intersections = Array(n) { IntArray(n) { -1 } }
        val size = findIntersections(intersections)
        val graph = Array(size) { IntArray(size) { -1 } }
        val visited = BooleanArray(size)

        for (i in intersections.indices) {
            for (j in intersections.indices) {
                val u = intersections[i][j]

                if (u != -1) {
                    findEdges(graph, intersections, u, j, i)
                }
            }
        }

        return longestPath(graph, visited, 0, 0)
    }
}
