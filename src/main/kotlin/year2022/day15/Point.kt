package year2022.day15

import kotlin.math.abs

class Point(val x: Int, val y: Int) {
    fun taxicabDistance(point: Point): Int =
        abs(x - point.x) + abs(y - point.y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Point
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}
