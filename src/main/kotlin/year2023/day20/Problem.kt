package year2023.day20

import IProblem

class Problem : IProblem {
    private val nodes = mutableMapOf<String, Module>()
    private val inward = mutableMapOf<String, MutableSet<String>>()
    private val outward = mutableMapOf<String, LinkedHashSet<String>>()
    private val queue = ArrayDeque<Pulse>()

    init {
        javaClass
            .getResourceAsStream("/2023/20.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val (left, right) = line.split(" -> ")
                val connections = right.split(", ")

                val m = when (line[0]) {
                    '%' -> {
                        val ff = FlipFlop()
                        ff.name = left.substring(1)
                        ff.onSignal = { handleSignal(ff, it) }
                        ff
                    }
                    '&' -> {
                        val cj = Conjunction()
                        cj.name = left.substring(1)
                        cj.onSignal = { handleSignal(cj, it) }
                        cj
                    }
                    else -> {
                        val br = Broadcaster()
                        br.name = left
                        br.onSignal = { handleSignal(br, it) }
                        br
                    }
                }

                val u = m.name
                nodes[u] = m
                outward[u] = LinkedHashSet(connections)

                for (v in connections) {
                    inward
                        .computeIfAbsent(v) { mutableSetOf() }
                        .add(u)
                }
            }
    }

    private fun handleSignal(ff: FlipFlop, pulse: Pulse) {
        if (pulse.intensity) {
            return
        }

        val u = ff.name
        ff.on = !ff.on

        for (v in outward[u]!!) {
            queue.addLast(Pulse(u, v, ff.on))
        }
    }

    private fun handleSignal(cj: Conjunction, pulse: Pulse) {
        cj.memo[pulse.src!!] = pulse.intensity

        val u = cj.name
        val intensity = cj.next(inward[u]!!)

        for (v in outward[u]!!) {
            queue.addLast(Pulse(u, v, intensity))
        }
    }

    private fun handleSignal(br: Broadcaster, pulse: Pulse) {
        val u = br.name

        for (v in outward[u]!!) {
            queue.addLast(Pulse(u, v, pulse.intensity))
        }
    }

    private fun push(fn: (Pulse) -> Unit) {
        queue.add(Pulse(null, "broadcaster", false))

        while (queue.isNotEmpty()) {
            val pulse = queue.removeFirst()
            val module = nodes[pulse.dest]

            module?.onSignal?.invoke(pulse)
            fn(pulse)
        }
    }

    private fun gcd(a0: Long, b0: Long): Long {
        var a = a0
        var b = b0

        while (b != 0L) {
            val t = b
            b = a % b
            a = t
        }

        return a
    }

    private fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

    override fun part1(): Int {
        var low = 0
        var high = 0

        for (i in 0 until 1000) {
            push {
                when (it.intensity){
                    false -> low++
                    true -> high++
                }
            }
        }

        return low * high
    }

    override fun part2(): Long {
        val cycles = mutableMapOf<String, Int>()
        val central = inward["rx"]!!.first()
        val n = nodes
            .entries
            .stream()
            .filter { outward[it.key]!!.contains(central) }
            .count()
            .toInt()
        var i = 0

        for (module in nodes.values) {
            module.reset()
        }

        while (cycles.size < n) {
            push {
                if (it.dest == central && it.intensity) {
                    cycles.computeIfAbsent(it.src!!) { i }
                }
            }

            i++
        }

        return cycles.values.fold(1L) { acc, x -> lcm(acc, x + 1L) }
    }
}
