package year2022.day17

class Piece(val rocks: Array<Point>) {
    private val left = rocks.minBy { it.x }
    private val top = rocks.maxBy { it.y }
    private val right = rocks.maxBy { it.x }
    private val bottom = rocks.minBy { it.y }
    var grounded = false

    fun height() = top.y

    fun move(x: Int, y: Int) {
        for (rock in rocks) {
            rock.x += x
            rock.y += y
        }
    }

    fun shiftLeft(matrix: Array<BooleanArray>) {
        if (left.x == 0 || rocks.any { matrix[matrix.size - it.y - 1][it.x - 1] }) {
            return
        }

        move(-1, 0)
    }

    fun shiftRight(matrix: Array<BooleanArray>) {
        if (right.x == WIDTH - 1 || rocks.any { matrix[matrix.size - it.y - 1][it.x + 1] }) {
            return
        }

        move(1, 0)
    }

    fun shiftDown(matrix: Array<BooleanArray>) {
        if (bottom.y == 0 || rocks.any { matrix[matrix.size - it.y][it.x] }) {
            grounded = true
            return
        }

        move(0, -1)
    }

    fun copy(): Piece {
        return Piece(Array(rocks.size) { rocks[it].copy() })
    }
}
