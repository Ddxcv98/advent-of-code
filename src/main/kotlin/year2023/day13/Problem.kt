package year2023.day13

import IProblem

class Problem : IProblem {
    private val maps = mutableListOf<Array<CharArray>>()

    init {
        javaClass
            .getResourceAsStream("/2023/13.txt")!!
            .bufferedReader()
            .use { br ->
                val matrix = mutableListOf<CharArray>()

                while (true) {
                    val line = br.readLine() ?: break

                    if (line.isEmpty()) {
                        maps.add(matrix.toTypedArray())
                        matrix.clear()
                    } else {
                        matrix.add(line.toCharArray())
                    }
                }

                maps.add(matrix.toTypedArray())
            }
    }

    private fun verticalReflection(matrix: Array<CharArray>, j: Int): Boolean {
        var l = j - 1
        var r = j

        while (l >= 0 && r < matrix[0].size) {
            for (i in matrix.indices) {
                if (matrix[i][l] != matrix[i][r]) {
                    return false
                }
            }

            l--
            r++
        }

        return true
    }

    private fun verticalReflections(matrix: Array<CharArray>) = sequence {
        for (j in 1 until matrix[0].size) {
            if (verticalReflection(matrix, j)) {
                yield(Pair(Reflection.VERTICAL, j))
            }
        }
    }

    private fun horizontalReflection(matrix: Array<CharArray>, i: Int): Boolean {
        var u = i - 1
        var d = i

        while (u >= 0 && d < matrix.size) {
            for (j in matrix[0].indices) {
                if (matrix[u][j] != matrix[d][j]) {
                    return false
                }
            }

            u--
            d++
        }

        return true
    }

    private fun horizontalReflections(matrix: Array<CharArray>) = sequence {
        for (i in 1 until matrix.size) {
            if (horizontalReflection(matrix, i)) {
                yield(Pair(Reflection.HORIZONTAL, i))
            }
        }
    }

    private fun findReflections(matrix: Array<CharArray>) = sequence {
        yieldAll(verticalReflections(matrix))
        yieldAll(horizontalReflections(matrix))
    }

    private fun mutatedReflection(matrix: Array<CharArray>): Pair<Reflection, Int> {
        val ref0 = findReflections(matrix).first()

        for (row in matrix) {
            for (j in row.indices) {
                row[j] = row[j].code.xor(13).toChar()

                val ref1 = findReflections(matrix).find { it != ref0 }

                if (ref1 != null) {
                    return ref1
                }

                row[j] = row[j].code.xor(13).toChar()
            }
        }

        return ref0
    }

    override fun part1(): Int {
        var res = 0

        for (matrix in maps) {
            val (type, idx) = findReflections(matrix).first()

            res += when (type) {
                Reflection.VERTICAL -> idx
                Reflection.HORIZONTAL -> idx * 100
            }
        }

        return res
    }

    override fun part2(): Int {
        var res = 0

        for (matrix in maps) {
            val (type, idx) = mutatedReflection(matrix)

            res += when (type) {
                Reflection.VERTICAL -> idx
                Reflection.HORIZONTAL -> idx * 100
            }
        }

        return res
    }
}
