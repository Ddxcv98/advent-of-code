package year2023.day05

class Offset(val destStart: Long, val srcStart: Long, val length: Long) {
    val srcEnd = srcStart + length
    val destEnd = destStart + length
    val diff = destStart - srcStart

    fun inverted(): Offset {
        return Offset(srcStart, destStart, length)
    }
}
