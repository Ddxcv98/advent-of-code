package year2022.day22

import util.rotatedCounterClockwise

class CubeWalker2D(right: Array<Array<Char>>) : CubeWalker<Pos2D> {
    private val map: Array<Array<Array<Char>>>

    init {
        val down = rotatedCounterClockwise(right)
        val left = rotatedCounterClockwise(down)
        val up = rotatedCounterClockwise(left)
        map = arrayOf(right, down, left, up)
    }

    override fun walk(pos: Pos2D, steps: Int) {
        val array = map[pos.r][pos.y]
        val start = array.indexOfFirst { it != ' ' }
        val end = array.indexOfLast { it != ' ' }
        val length = end - start + 1

        for (i in 0 until steps) {
            val next = (pos.x + 1 - start).mod(length) + start

            if (array[next] == '#') {
                break
            }

            pos.x = next
        }
    }

    override fun rotate(pos: Pos2D, r: Char) {
        val matrix = map[pos.r]

        when (r) {
            'L' -> pos.rotateClockwise(matrix.size)
            'R' -> pos.rotateCounterClockwise(matrix[0].size)
        }
    }

    override fun toPos2D(pos: Pos2D): Pos2D {
        return pos
    }
}
