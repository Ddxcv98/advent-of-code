package year2022.day14

import solve

data class Point(val x: Int, val y: Int)

const val ROCK = '#'
const val SAND = '+'
const val AIR  = '.'

val DROP_ORDER = arrayOf(0, -1, 1)
val START = Point(500, 0)

fun main() {
    solve { Problem() }
}
