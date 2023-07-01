package year2022.day25

import IProblem
import util.`^`

class Problem : IProblem {
    private val snafu = javaClass
        .getResourceAsStream("/2022/25.txt")!!
        .bufferedReader()
        .readLines()

    private fun snafuToDecimal(s: String) = s
        .toCharArray()
        .also(CharArray::reverse)
        .foldIndexed(0L) { i, acc, c -> acc + (5 `^` i) * SNAFU_DEC[c]!! }

    private fun decimalToSnafu(n: Long): String {
        val deque = ArrayDeque<Char>()
        var num = n

        while (num != 0L) {
            deque.addFirst(DEC_SNAFU[num.mod(5)]!!)
            num = (num + 2) / 5
        }

        return String(deque.toCharArray())
    }

    override fun part1(): String {
        val sum = snafu
            .map(::snafuToDecimal)
            .fold(0L) { acc, n -> acc + n }

        return decimalToSnafu(sum)
    }

    override fun part2(): Int {
        return 0
    }
}
