package year2022.day12

class Node(val c: Char, val x: Int, val y: Int) {
    fun canComeFrom(node: Node): Boolean {
        return c - node.c <= 1
    }

    fun toTriple(): Triple<Char, Int, Int> {
        return Triple(c, x, y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Node
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }
}
