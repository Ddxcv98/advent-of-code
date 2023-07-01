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

    private fun maxFlow(source: Valve, time: Int, closed: Collection<Valve>): Int {
        if (time > TIME) {
            return 0
        }

        var flow = 0

        for (dest in closed) {
            val copy = closed.toMutableSet().also { it.remove(dest) }
            flow = max(flow, maxFlow(dest, time + dist[source.id][dest.id] + 1, copy))
        }

        return source.rate * (TIME - time) + flow
    }

    private fun allPaths(source: Valve, time: Int, closed: Collection<Valve>, flow: Int, path: List<Valve>, paths: MutableList<Pair<Int, List<Valve>>>) {
        if (time > TIME) {
            return
        }

        val flow0 = flow + source.rate * (TIME - time)

        for (dest in closed) {
            val time0 = time + dist[source.id][dest.id] + 1
            val closed0 = closed.toMutableSet().also { it.remove(dest) }
            val path0 = path.toMutableList().also { it.add(dest) }
            allPaths(dest, time0, closed0, flow0, path0, paths)
        }

        paths.add(Pair(flow0, path))
    }

    override fun part1(): Int {
        val start = valves[START]!!
        val closed = valves.values.filter { it.rate != 0 }
        return maxFlow(start, 0, closed)
    }

    override fun part2(): Int {
        val start = valves[START]!!
        val closed = valves.values.filter { it.rate != 0 }
        val paths = mutableListOf<Pair<Int, List<Valve>>>()
        var flow = 0

        allPaths(start, 4, closed, 0, listOf(), paths)

        paths.sortByDescending(Pair<Int, List<Valve>>::first)

        for (i in paths.indices) {
            val s = paths[i].second.toSet()

            for (j in i + 1 until paths.size) {
                if (paths[i].first + paths[j].first <= flow) {
                    break
                }

                if (paths[j].second.none { it in s }) {
                   flow = paths[i].first + paths[j].first
                }
            }
        }

        return flow
    }
}
