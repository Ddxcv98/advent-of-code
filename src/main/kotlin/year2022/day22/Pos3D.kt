package year2022.day22

class Pos3D(var f: Int, r: Int, x: Int, y: Int) : Pos2D(r, x, y) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass || !super.equals(other)) return false

        other as Pos3D

        return f == other.f
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + f
        return result
    }

    override fun toString(): String {
        return "Pos3D(f=$f, r=$r, x=$x, y=$y)"
    }
}
