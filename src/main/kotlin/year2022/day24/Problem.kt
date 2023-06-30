package year2022.day24

import IProblem

class Problem : IProblem {
    private val blizzards = mutableListOf<Blizzard>()

    private val map = javaClass
        .getResourceAsStream("/2022/24.txt")!!
        .bufferedReader()
        .readLines()
        .mapIndexed { i, line ->
            line.mapIndexed { j, c ->
                when (c) {
                    '.' -> 0
                    '#' -> -1
                    else -> {
                        blizzards.add(Blizzard.of(j, i, c))
                        1
                    }
                }
            }
        }

    private val start = Point(map.first().indexOfFirst { it == 0 }, 0)
    private val end = Point(map.last().indexOfFirst { it == 0 }, map.lastIndex)

    private fun moves(p: Point): Sequence<Point> {
        return sequence {
            yield(p.copy(y = p.y - 1))
            yield(p.copy(y = p.y + 1))
            yield(p.copy(x = p.x - 1))
            yield(p.copy(x = p.x + 1))
            yield(p.copy())
        }
    }

    private fun traverse(matrix: Array<IntArray>, blizzards: Array<Blizzard>, src: Point, dest: Point): Int {
        val queue0 = ArrayDeque(listOf(src))
        val queue1 = ArrayDeque<Point>()
        var minute = 0

        while (true) {
            val visited = Array(matrix.size) { BooleanArray(matrix[0].size) }

            val (curr, next) = if (minute and 1 == 0) {
                Pair(queue0, queue1)
            } else {
                Pair(queue1, queue0)
            }

            for (blizzard in blizzards) {
                blizzard.move(matrix)
            }

            while (curr.isNotEmpty()) {
                val p = curr.removeFirst()

                if (p == dest) {
                    return minute
                }

                for (q in moves(p)) {
                    if (q.y in 0..matrix.lastIndex && matrix[q.y][q.x] == 0 && !visited[q.y][q.x]) {
                        visited[q.y][q.x] = true
                        next.addLast(q)
                    }
                }
            }

            minute++
        }
    }

    override fun part1(): Int {
        val matrix = Array(map.size) { i ->  IntArray(map[i].size) { j -> map[i][j] } }
        val blizzards = Array(blizzards.size) { i-> blizzards[i].copy() }
        return traverse(matrix, blizzards, start, end)
    }

    override fun part2(): Int {
        val matrix = Array(map.size) { i ->  IntArray(map[i].size) { j -> map[i][j] } }
        val blizzards = Array(blizzards.size) { i-> blizzards[i].copy() }
        return traverse(matrix, blizzards, start, end) +
                traverse(matrix, blizzards, end, start) +
                traverse(matrix, blizzards, start, end) + 2
    }
}
