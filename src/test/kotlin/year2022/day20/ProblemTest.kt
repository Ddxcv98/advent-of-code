package year2022.day20

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(3, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(0, problem.part2())
    }

    @Test
    fun AFFFFF() {
        val nodes = listOf(
            Node(0, null, null),
            Node(1, null, null),
            Node(2, null, null),
            Node(3, null, null),
            Node(4, null, null),
            Node(5, null, null),
            Node(6, null, null),
            Node(7, null, null),
            Node(8, null, null),
            Node(9, null, null)
        )

        for ((i, node) in nodes.withIndex()) {
            node.prev = nodes[(i - 1).mod(nodes.size)]
            node.next = nodes[(i + 1).mod(nodes.size)]
        }

        val map = Array<MutableList<Int>>(nodes.size) { mutableListOf() }
        var curr = nodes.first()

        for (i in 0 until 1000) {
            map[curr.value.toInt()].add(i)
            curr = curr.prev!!
        }

        println(map.joinToString("\n"))
    }
}
