package year2022.day25

import solve

val SNAFU_DEC = mapOf(
    Pair('=', -2),
    Pair('-', -1),
    Pair('0', 0),
    Pair('1', 1),
    Pair('2', 2),
)

val DEC_SNAFU = mapOf(
    Pair(0, '0'),
    Pair(1, '1'),
    Pair(2, '2'),
    Pair(3, '='),
    Pair(4, '-')
)

fun main() {
    solve { Problem() }
}
