package year2022.day22

import util.intSqrt
import util.rotatedCounterClockwise

class CubeWalker3D(
    private val transitions: Array<Array<Pair<Int, Int>>>,
    matrix: Array<Array<Char>>
) : CubeWalker<Pos3D> {
    private val n: Int = intSqrt(matrix.sumOf { chars -> chars.count { c -> c != ' ' } } / 6)
    private val faces = Array(6) { Array(4) { Array(n) { Array(n) { '\u0000' } } } }
    private val offsets = arrayOfNulls<Pair<Int, Int>>(6)

    init {
        var f = 0

        for (i in matrix.indices step n) {
            for (j in matrix[i].indices step n) {
                if (matrix[i][j] == ' ') {
                    continue
                }

                val face = faces[f]

                for (k in 0 until n) {
                    for (l in 0 until n) {
                        face[0][k][l] = matrix[i + k][j + l]
                    }
                }

                face[1] = rotatedCounterClockwise(face[0])
                face[2] = rotatedCounterClockwise(face[1])
                face[3] = rotatedCounterClockwise(face[2])
                offsets[f++] = Pair(j, i)
            }
        }
    }

    override fun walk(pos: Pos3D, steps: Int) {
        var f = pos.f
        var r = pos.r

        for (i in 0 until steps) {
            val x = (pos.x + 1).mod(n)

            if (x == 0) {
                val pair = transitions[f][r]
                f = pair.first
                r = pair.second
            }

            if (faces[f][r][pos.y][x] == '#') {
                break
            }

            pos.f = f
            pos.r = r
            pos.x = x
        }
    }

    override fun rotate(pos: Pos3D, r: Char) {
        when (r) {
            'L' -> pos.rotateClockwise(n)
            'R' -> pos.rotateCounterClockwise(n)
        }
    }

    override fun toPos2D(pos: Pos3D): Pos2D {
        val (offsetX, offsetY) = offsets[pos.f]!!
        return Pos2D(pos.r, pos.x + offsetX, pos.y + offsetY)
    }
}
