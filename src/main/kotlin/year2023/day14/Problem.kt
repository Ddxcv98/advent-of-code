package year2023.day14

import IProblem

class Problem : IProblem {
    private val lines = javaClass
        .getResourceAsStream("/2023/14.txt")!!
        .bufferedReader()
        .lines()
        .toList()

    private fun north(matrix: Array<CharArray>) {
        for (j in matrix[0].indices) {
            var idx = 0

            for (i in matrix.indices) {
                when (matrix[i][j]) {
                    '#' -> idx = i + 1
                    'O' -> {
                        matrix[i][j] = '.'
                        matrix[idx++][j] = 'O'
                    }
                }
            }
        }
    }

    private fun west(matrix: Array<CharArray>) {
        for (i in matrix.indices) {
            var idx = 0

            for (j in matrix[0].indices) {
                when (matrix[i][j]) {
                    '#' -> idx = j + 1
                    'O' -> {
                        matrix[i][j] = '.'
                        matrix[i][idx++] = 'O'
                    }
                }
            }
        }
    }

    private fun south(matrix: Array<CharArray>) {
        for (j in matrix[0].indices) {
            var idx = matrix.size - 1

            for (i in matrix.indices.reversed()) {
                when (matrix[i][j]) {
                    '#' -> idx = i - 1
                    'O' -> {
                        matrix[i][j] = '.'
                        matrix[idx--][j] = 'O'
                    }
                }
            }
        }
    }

    private fun east(matrix: Array<CharArray>) {
        for (i in matrix.indices) {
            var idx = matrix.size - 1

            for (j in matrix[0].indices.reversed()) {
                when (matrix[i][j]) {
                    '#' -> idx = j - 1
                    'O' -> {
                        matrix[i][j] = '.'
                        matrix[i][idx--] = 'O'
                    }
                }
            }
        }
    }

    private fun weight(matrix: Array<CharArray>): Int {
        var w = 0

        for (i in matrix.indices) {
            w += matrix[i].count { it == 'O' } * (matrix.size - i)
        }

        return w
    }

    override fun part1(): Int {
        val matrix = Array(lines.size) { i -> lines[i].toCharArray() }
        north(matrix)
        return weight(matrix)
    }

    override fun part2(): Int {
        val matrix = Array(lines.size) { i -> lines[i].toCharArray() }
        val snapshots = mutableMapOf<String, Int>()
        val weights = mutableListOf<Int>()
        var j = 0

        while (true) {
            val key = matrix.joinToString("") { it.joinToString("") }
            val i = snapshots[key]

            if (i != null) {
                return weights[(1_000_000_000 - i) % (j - i) + i]
            }

            snapshots[key] = j++
            weights.add(weight(matrix))

            north(matrix)
            west(matrix)
            south(matrix)
            east(matrix)
        }
    }
}
