package year2023.day17

import IProblem
import java.util.PriorityQueue

class Problem : IProblem {
    private val matrix = javaClass
        .getResourceAsStream("/2023/17.txt")!!
        .bufferedReader()
        .lines()
        .map { line -> line.map(Char::digitToInt).toIntArray() }
        .toList()
        .toTypedArray()

    private val n = matrix.size

    private fun bfs(min: Int, max: Int): Int {
        val queue = PriorityQueue<Pair<Pos, Int>>(Comparator.comparingInt { it.second })
        val visited = mutableSetOf<Pos>()
        queue.offer(Pair(Pos(0, 0, 0, '>'), 0))
        queue.offer(Pair(Pos(0, 0, 0, 'v'), 0))

        while (queue.isNotEmpty()) {
            val (u, dist) = queue.poll()

            if (u.x == n - 1 && u.y == n - 1 && u.size >= min) {
                return dist
            }

            for (v in u.walk()) {
                if (v.x in 0..<n &&
                    v.y in 0..<n &&
                    (u.size < min && u.dir == v.dir || u.size >= min && v.size <= max) &&
                    visited.add(v)) {
                    queue.offer(Pair(v, dist + matrix[v.y][v.x]))
                }
            }
        }

        return -1
    }

    override fun part1(): Int {
        return bfs(1, 3)
    }

    override fun part2(): Int {
        return bfs(4, 10)
    }
}
