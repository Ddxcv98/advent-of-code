package year2023.day03

import IProblem

class Problem : IProblem {
    private val nonSymbols = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')

    private val matrix: Array<Array<Char>>
    private val n: Int
    private val m: Int

    init {
        val lines = mutableListOf<CharArray>()

        javaClass
            .getResourceAsStream("/2023/03.txt")!!
            .bufferedReader()
            .use {
                while (true) {
                    val line = it.readLine() ?: break
                    lines.add(line.toCharArray())
                }
            }

        n = lines.size
        m = lines[0].size
        matrix = Array(n) { lines[it].toTypedArray() }
    }

    private fun hasAdjacentSymbol(matrix: Array<Array<Char>>, i: Int, j: Int): Boolean {
        return i != 0 && j != 0 && matrix[i - 1][j - 1] !in nonSymbols ||
               i != 0 && matrix[i - 1][j] !in nonSymbols ||
               i != 0 && j != m - 1 && matrix[i - 1][j + 1] !in nonSymbols ||
               j != 0 && matrix[i][j - 1] !in nonSymbols ||
               j != m - 1 && matrix[i][j + 1] !in nonSymbols ||
               i != n - 1 && j != 0 && matrix[i + 1][j - 1] !in nonSymbols ||
               i != n - 1 && matrix[i + 1][j] !in nonSymbols ||
               i != n - 1 && j != m - 1 && matrix[i + 1][j + 1] !in nonSymbols
    }

    private fun parseNumber(nums: MutableList<Int>, matrix: Array<Array<Char>>, visited: MutableSet<Pair<Int, Int>>, i: Int, j: Int) {
        if (i < 0 || i >= n || j < 0 || j >= m || !matrix[i][j].isDigit() || Pair(i, j) in visited) {
            return
        }

        var x = 0
        var e = 1

        for (k in j - 1 downTo 0) {
            if (!matrix[i][k].isDigit()) {
                break
            }

            x += matrix[i][k].digitToInt() * e
            e *= 10
            visited.add(Pair(i, k))
        }

        for (k in j until m) {
            if (!matrix[i][k].isDigit()) {
                break
            }

            x = x * 10 + matrix[i][k].digitToInt()
            visited.add(Pair(i, k))
        }

        nums.add(x)
    }

    private fun gearRatio(matrix: Array<Array<Char>>, i: Int, j: Int): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val nums = mutableListOf<Int>()

        parseNumber(nums, matrix, visited, i - 1, j - 1)
        parseNumber(nums, matrix, visited, i - 1, j)
        parseNumber(nums, matrix, visited, i - 1, j + 1)
        parseNumber(nums, matrix, visited, i, j - 1)
        parseNumber(nums, matrix, visited, i, j + 1)
        parseNumber(nums, matrix, visited, i + 1, j - 1)
        parseNumber(nums, matrix, visited, i + 1, j)
        parseNumber(nums, matrix, visited, i + 1, j + 1)

        if (nums.size != 2) {
            return 0
        }

        return nums.fold(1) { acc, x -> acc * x }
    }

    override fun part1(): Int {
        var parsing = false
        var symbol = false
        var sum = 0
        var x = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                val c = matrix[i][j]

                if (parsing) {
                    if (c.isDigit()) {
                        if (!symbol && hasAdjacentSymbol(matrix, i, j)) {
                            symbol = true
                        }

                        x = x * 10 + c.digitToInt()
                    } else {
                        if (symbol) {
                            sum += x
                        }

                        parsing = false
                    }
                } else if (c.isDigit()) {
                    parsing = true
                    symbol = hasAdjacentSymbol(matrix, i, j)
                    x = c.digitToInt()
                }
            }
        }

        return sum
    }

    override fun part2(): Int {
        var sum = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (matrix[i][j] == '*') {
                    sum += gearRatio(matrix, i, j)
                }
            }
        }

        return sum
    }
}
