package year2022.day20

import IProblem

class Problem : IProblem {
    private val input = javaClass
        .getResource("/2022/20.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)
        .map(String::toLong)

    private fun nthForward(node: Node, n: Int): Node {
        var curr = node

        for (i in 0 until n) {
            curr = curr.next!!
        }

        return curr
    }

    private fun sum(nodes: Array<Node>): Long {
        var curr = nodes.first()
        var n = 0L

        while (curr.value != 0L) {
            curr = curr.next!!
        }

        for (i in 0 until 3) {
            curr = nthForward(curr, 1000)
            n += curr.value
        }

        return n
    }

    private fun decrypt(list: List<Long>, shuffles: Int): Long {
        val nodes = Array(list.size) { Node(list[it], null, null) }

        for ((i, node) in nodes.withIndex()) {
            node.prev = nodes[(i - 1).mod(input.size)]
            node.next = nodes[(i + 1).mod(input.size)]
        }

        for (i in 0 until shuffles) {
            for (node in nodes) {
                val n = node.value.mod(nodes.size - 1)

                if (n == 0) {
                    continue
                }

                val curr = nthForward(node, n)

                node.prev!!.next = node.next
                node.next!!.prev = node.prev
                node.prev = curr
                node.next = curr.next!!
                curr.next!!.prev = node
                curr.next = node
            }
        }

        return sum(nodes)
    }

    override fun part1(): Long {
        return decrypt(input, 1)
    }

    override fun part2(): Long {
        return decrypt(input.map { it * 811589153 }, 10)
    }
}
