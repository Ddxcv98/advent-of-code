package year2022.day22

open class Pos2D(var r: Int, var x: Int, var y: Int) {
    fun rotateClockwise(length: Int) {
        val x0 = x
        val y0 = y

        r = (r - 1).mod(4)
        x = length - y0 - 1
        y = x0
    }

    fun rotateCounterClockwise(length: Int) {
        val x0 = x
        val y0 = y

        r = (r + 1).mod(4)
        x = y0
        y = length - x0 - 1
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pos2D

        return r == other.r && x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = r
        result = 31 * result + x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "Pos2D(r=$r, x=$x, y=$y)"
    }
}
