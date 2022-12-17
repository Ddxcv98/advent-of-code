fun String.substr(start: Int, end: Int): String {
    val i = start.mod(this.length)
    val j = end.mod(this.length)
    if (i > j) return ""
    return this.substring(i, j)
}

fun <E> List<E>.sublst(start: Int, end: Int): List<E> {
    val i = start.mod(this.size)
    val j = end.mod(this.size)
    if (i > j) return emptyList()
    return this.subList(i, j)
}
