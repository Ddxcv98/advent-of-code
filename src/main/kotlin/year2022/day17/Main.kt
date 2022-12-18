package year2022.day17

import solve

data class Point(var x: Int, var y: Int)
data class Indexes(val piece: Int, val jet: Int)
data class Stats(val height: Int, val pieces: Int)

const val WIDTH = 7
const val OFFSET_X = 2
const val OFFSET_Y = 3

val PIECES = arrayOf(
    Piece(
        arrayOf(
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0)
        )
    ),
    Piece(
        arrayOf(
            Point(1, 2),
            Point(0, 1),
            Point(1, 1),
            Point(2, 1),
            Point(1, 0)
        )
    ),
    Piece(
        arrayOf(
            Point(2, 2),
            Point(2, 1),
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
        )
    ),
    Piece(
        arrayOf(
            Point(0, 3),
            Point(0, 2),
            Point(0, 1),
            Point(0, 0)
        )
    ),
    Piece(
        arrayOf(
            Point(0, 1),
            Point(1, 1),
            Point(0, 0),
            Point(1, 0)
        )
    )
)

fun main() {
    solve { Problem() }
}
