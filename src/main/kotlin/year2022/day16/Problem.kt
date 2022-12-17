package year2022.day16

import IProblem
import sublst
import substr
import kotlin.math.max

class Problem : IProblem {
    private val valves = linkedMapOf<String, Valve>()
    private val dist: Array<IntArray>

    init {
        val parents = mutableMapOf<String, MutableSet<Valve>>()
        var i = 0

        javaClass
            .getResourceAsStream("/2022/16.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val split = line.split(' ')
                val name = split[1]
                val rate = split[4].substr(5, -1).toInt()
                val adjs = split.sublst(9, -1).map { it.substr(0, -1) } + split[split.lastIndex]
                val valve = Valve(i++, name, rate)

                valves[name] = valve
                parents[name]?.forEach { it.adjs.add(valve) }

                for (key in adjs) {
                    val parentSet = parents.computeIfAbsent(key) { mutableSetOf() }
                    val adj = valves[key]

                    if (adj == null) {
                        parentSet.add(valve)
                    } else {
                        valve.adjs.add(adj)
                    }
                }
            }

        dist = Array(valves.size) { IntArray(valves.size) { INFINITY } }
        floydWarshall()
    }

    private fun floydWarshall() {
        val n = valves.size

        for (valve in valves.values) {
            dist[valve.id][valve.id] = 0

            for (adj in valve.adjs) {
                dist[valve.id][adj.id] = 1
            }
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                    }
                }
            }
        }
    }

    private fun maxFlow(source: Valve, time: Int, closed: Set<Valve>): Int {
        if (time > TIME) {
            return 0
        }

        var flow = 0

        for (dest in closed) {
            val copy = closed.toMutableSet()
            copy.remove(dest)
            flow = max(flow, maxFlow(dest, time + dist[source.id][dest.id] + 1, copy))
        }

        return source.rate * (TIME - time) + flow
    }

    private fun assignedValves(list: List<Valve>, mask: UInt): Sequence<Valve> {
        return sequence {
            val iterator = list.iterator()
            var i = mask

             while (i != 0U) {
                 val valve = iterator.next()

                 if (i and 1U == 1U) {
                     yield(valve)
                 }

                 i = i shr 1
             }
        }
    }

    override fun part1(): Int {
        val start = valves[START]!!
        val closed = valves
            .values
            .filter { it.rate != 0 }
            .toSet()

        return maxFlow(start, 0, closed)
    }

    override fun part2(): Int {
        val start = valves[START]!!
        val closed = valves
            .values
            .filter { it.rate != 0 }
            .toList()

        val n = closed.size
        val mask = 1U shl n
        var flow = 0

        for (i in 0U until mask / 2U) {
            val me = assignedValves(closed, i).toSet()
            val el = assignedValves(closed, (mask - 1U) and i.inv()).toSet()
            flow = max(flow, maxFlow(start, 4, me) + maxFlow(start, 4, el))
        }

        return flow
    }
}
