package year2022.day20

import solve

class Node(val value: Long, var prev: Node?, var next: Node?) {
    override fun toString(): String {
        return "Node(value=$value)"
    }
}

fun main() {
    solve { Problem() }
}
