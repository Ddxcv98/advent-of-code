package year2023.day04

class Card(val id: Int) {
    val winning = mutableSetOf<Int>()
    val owned = mutableListOf<Int>()

    fun matches(): Int {
        return owned.count(winning::contains)
    }

    fun points(): Int {
        val count = matches()

        if (count == 0) {
            return 0
        }

        return 1 shl (count - 1)
    }
}
