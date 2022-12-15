package year2022.day12

import IProblem

class Problem : IProblem {
    private lateinit var source: Node
    private lateinit var dest: Node

    private val matrix = javaClass
        .getResource("/2022/12.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)
        .mapIndexed { i, line ->
            line
                .mapIndexed { j, char ->
                    val c = when (char) {
                        'S' -> 'a'
                        'E' -> 'z'
                        else -> char
                    }

                    val node = Node(c, i, j)

                    if (char == 'S') {
                        source = node
                    } else if (char == 'E') {
                        dest = node
                    }

                    node
                }
                .toTypedArray()
        }
        .toTypedArray()

    private fun adjOf(node: Node): Sequence<Node> {
        val (_, i, j) = node.toTriple()

        return sequence {
            if (j != 0 && node.canComeFrom(matrix[i][j - 1])) yield(matrix[i][j - 1])
            if (i != 0 && node.canComeFrom(matrix[i - 1][j])) yield(matrix[i - 1][j])
            if (j != matrix[i].lastIndex && node.canComeFrom(matrix[i][j + 1])) yield(matrix[i][j + 1])
            if (i != matrix.lastIndex && node.canComeFrom(matrix[i + 1][j])) yield(matrix[i + 1][j])
        }
    }

    private fun bfs(predicate: (Node) -> Boolean): Int {
        val dist = mutableMapOf(Pair(dest, 0))
        val queue = ArrayDeque<Node>()

        queue.add(dest)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val d = dist[node]!! + 1

            for (adj in adjOf(node)) {
                if (!dist.containsKey(adj)) {
                    dist[adj] = d

                    if (predicate(adj)) {
                        return d
                    }

                    queue.addLast(adj)
                }
            }
        }

        return -1
    }

    override fun part1(): Int {
        return bfs { it === source }
    }

    override fun part2(): Int {
        return bfs { it.c == 'a' }
    }
}
