package year2022.day16

class Valve(
    val id: Int,
    val name: String,
    val rate: Int,
) {
    val adjs = mutableListOf<Valve>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Valve
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return name
    }
}
