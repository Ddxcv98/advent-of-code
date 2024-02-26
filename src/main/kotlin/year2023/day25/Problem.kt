package year2023.day25

import IProblem

class Problem : IProblem {
    private val graph = mutableMapOf<String, MutableSet<String>>()

    init {
        javaClass
            .getResourceAsStream("/2023/25.txt")!!
            .bufferedReader()
            .forEachLine {
                val split = it.split(": ")
                val u = split[0]
                val set = graph.computeIfAbsent(u) { mutableSetOf() }

                for (v in split[1].split(' ')) {
                    set.add(v)

                    graph
                        .computeIfAbsent(v) { mutableSetOf() }
                        .add(u)
                }
            }
    }

    override fun part1(): Int {
        val s = graph.keys.toMutableSet()
        val t = mutableSetOf<String>()

        while (s.sumOf { u -> graph[u]!!.count { v -> v in t } } != 3) {
            val m = s.maxBy { u -> graph[u]!!.count { v -> v in t } }
            s.remove(m)
            t.add(m)
        }

        return s.size * t.size
    }

    override fun part2(): Int {
        return 0
    }
}
