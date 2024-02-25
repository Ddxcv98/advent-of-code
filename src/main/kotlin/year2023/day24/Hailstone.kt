package year2023.day24

import java.lang.Exception

class Hailstone(val x: Double,
                val y: Double,
                val z: Double,
                val vx: Double,
                val vy: Double,
                val vz: Double) {

    companion object {
        private var n = 0
    }

    val id = ++n
    val a = vy / vx
    val b = -a * x + y

    fun intersection(h: Hailstone): Pair<Double, Double>? {
        if (a == h.a) {
            if (b == h.b) {
                throw Exception("coincident! panic!")
            }

            return null
        }

        val x = (h.b - b) / (a - h.a)
        val y = a * x + b

        if (x - this.x < 0 != vx < 0 ||
            y - this.y < 0 != vy < 0 ||
            x - h.x < 0 != h.vx < 0 ||
            y - h.y < 0 != h.vy < 0) {
            return null
        }

        return Pair(x, y)
    }

    override fun toString(): String {
        return "Hailstone(id=$id, x=$x, y=$y, z=$z, vx=$vx, vy=$vy, vz=$vz, a=$a, b=$b)"
    }
}
