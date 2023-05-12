package year2022.day22

import IProblem
import java.io.BufferedReader

class Problem(transitions: Array<Array<Pair<Int, Int>>>) : IProblem {
    private val matrix: Array<Array<Char>>
    private val moves = mutableListOf<(Pair<Int, Char>)>()
    private val cw2D: CubeWalker2D
    private val cw3D: CubeWalker3D

    init {
        javaClass
            .getResourceAsStream("/2022/22.txt")!!
            .bufferedReader()
            .use {
                matrix = parseMap(it)
                parseMoves(it.readLine())
            }

        cw2D = CubeWalker2D(matrix)
        cw3D = CubeWalker3D(transitions, matrix)
    }

    private fun parseMap(br: BufferedReader): Array<Array<Char>> {
        val lines = mutableListOf<CharArray>()
        var width = 0

        while (true) {
            val line = br.readLine()

            if (line.isEmpty()) {
                break
            }

            val chars = line.toCharArray()

            if (chars.size > width) {
                width = chars.size
            }

            lines.add(chars)
        }

        return Array(lines.size) { i ->
            Array(width) { j ->
                if (j < lines[i].size) {
                    lines[i][j]
                } else {
                    ' '
                }
            }
        }
    }

    private fun parseMoves(s: String) {
        var n = 0

        for (char in s.toCharArray()) {
            n = if (char.isDigit()) {
                n * 10 + char.digitToInt()
            } else {
                moves.add(Pair(n, char))
                0
            }
        }

        moves.add(Pair(n, 'X'))
    }

    private fun <P : Pos2D> dance(cw: CubeWalker<P>, pos: P): Int {
        for ((n, r) in moves) {
            cw.walk(pos, n)
            cw.rotate(pos, r)
        }

        val facing = pos.r

        while (pos.r != 0) {
            cw.rotate(pos, 'L')
        }

        val pos2D = cw.toPos2D(pos)

        return 1000 * (pos2D.y + 1) + 4 * (pos2D.x + 1) + facing
    }

    override fun part1(): Int {
        val pos = Pos2D(0, matrix[0].indexOfFirst { it == '.' }, 0)
        return dance(cw2D, pos)
    }

    override fun part2(): Int {
        val pos = Pos3D(0, 0, 0, 0)
        return dance(cw3D, pos)
    }
}
