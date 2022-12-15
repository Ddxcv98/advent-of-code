package year2022.day07

class File(
    val parent: File? = null,
    val name: String? = null,
    private var size: ULong? = null,
    val children: MutableMap<String, File>? = null
) {
    private fun calcSize(): ULong {
        var size = 0UL

        for (entry in iterdir()) {
            size += entry.size()
        }

        return size
    }

    fun isdir() = children != null

    fun iterdir() = if (isdir()) { children!!.values.asSequence() } else { emptySequence() }

    fun size(): ULong {
        if (size == null) {
            size = calcSize()
        }

        return size!!
    }
}
