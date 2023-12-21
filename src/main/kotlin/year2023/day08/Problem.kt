package year2023.day08

import IProblem
import java.lang.Exception
import java.util.*
import java.util.stream.Collectors

class Problem : IProblem {
    private val steps: CharArray
    private val nodes = mutableMapOf<String, Pair<String, String>>()

    init {
        javaClass
            .getResourceAsStream("/2023/08.txt")!!
            .bufferedReader()
            .use { br ->
                steps = br.readLine().toCharArray()
                br.readLine()

                while (true) {
                    val line = br.readLine() ?: break
                    val split = line.split(Regex("\\W+"))
                    nodes[split[0]] = Pair(split[1], split[2])
                }
            }
    }

    private fun pathSteps(src: String, dest: Collection<String>): Int {
        var node = src
        var i = 0

        while (node !in dest) {
            node = when (steps[i % steps.size]) {
                'L' -> nodes[node]!!.first
                'R' -> nodes[node]!!.second
                else -> throw Exception("bruh moment")
            }

            i++
        }

        return i
    }

    private fun gcd(a: Long, b: Long): Long {
        var a = a
        var b = b

        while (b != 0L) {
            val t = b
            b = a % b
            a = t
        }

        return a
    }

    private fun lcd(a: Long, b: Long): Long {
        return  (a * b) / gcd(a, b)
    }

    override fun part1(): Int {
        return pathSteps("AAA", Collections.singletonList("ZZZ"))
    }

    override fun part2(): Long {
        val fn = { a: Long, b: Long -> lcd(a, b) }

        val dest = nodes
            .keys
            .stream()
            .filter { it.endsWith('Z') }
            .collect(Collectors.toSet())

        return nodes
            .keys
            .stream()
            .filter { it.endsWith('A') }
            .map { pathSteps(it, dest).toLong() }
            .reduce(1L, fn, fn)
    }
}
