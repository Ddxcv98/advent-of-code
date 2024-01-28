package year2023.day21

import IProblem

class Problem(private val steps: Int = 64) : IProblem {
    private val matrix = javaClass
        .getResourceAsStream("/2023/21.txt")!!
        .bufferedReader()
        .lines()
        .map(String::toCharArray)
        .toList()
        .toTypedArray()

    private val n = matrix.size

    private val start = startPos()

    private fun startPos(): Pair<Int, Int> {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (matrix[i][j] == 'S') {
                    return Pair(j, i)
                }
            }
        }

        return Pair(0, 0)
    }

    private fun walk(x: Int, y: Int, c: Int) = sequence {
        if (matrix[y.mod(n)][(x - 1).mod(n)] != '#') {
            yield(Triple(x - 1, y, c + 1))
        }

        if (matrix[y.mod(n)][(x + 1).mod(n)] != '#') {
            yield(Triple(x + 1, y, c + 1))
        }

        if (matrix[(y - 1).mod(n)][x.mod(n)] != '#') {
            yield(Triple(x, y - 1, c + 1))
        }

        if (matrix[(y + 1).mod(n)][x.mod(n)] != '#') {
            yield(Triple(x, y + 1, c + 1))
        }
    }

    private fun plots(steps: Int): Int {
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        val visited = mutableSetOf<Pair<Int, Int>>()
        val mask = steps and 1
        var count = 0

        queue.add(Triple(start.first, start.second, 0))
        visited.add(start)

        while (queue.isNotEmpty()) {
            val (x, y, c) = queue.removeFirst()

            if (c and 1 == mask) {
                count++
            }

            if (c == steps) {
                continue
            }

            for (triple in walk(x, y, c)) {
                val pair = Pair(triple.first, triple.second)

                if (visited.add(pair)) {
                    queue.addLast(triple)
                }
            }
        }

        return count
    }

    override fun part1(): Int {
        return plots(steps)
    }

    override fun part2(): Long {
        val a = n
        val b = n / 2
        val p = Array(3) { x -> Pair(x, plots(a * x + b)) }
        val x = (26501365 - b) / a.toLong()

        return ((x - p[1].first) * (x - p[2].first)) / ((p[0].first - p[1].first) * (p[0].first - p[2].first)) * p[0].second +
               ((x - p[0].first) * (x - p[2].first)) / ((p[1].first - p[0].first) * (p[1].first - p[2].first)) * p[1].second +
               ((x - p[0].first) * (x - p[1].first)) / ((p[2].first - p[0].first) * (p[2].first - p[1].first)) * p[2].second
    }
}
