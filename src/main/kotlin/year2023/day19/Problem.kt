package year2023.day19

import IProblem
import substr
import java.io.BufferedReader
import java.util.Collections
import java.util.Objects

class Problem : IProblem {
    private val workflows = mutableMapOf<String, List<IRule>>()
    private val ratings = mutableListOf<Rating>()

    init {
        javaClass
            .getResourceAsStream("/2023/19.txt")!!
            .bufferedReader()
            .use {
                parseWorkflows(it)
                parseRatings(it)
            }
    }

    private fun parseWorkflows(br: BufferedReader) {
        while (true) {
            val line = br.readLine()

            if (line.isEmpty()) {
                break
            }

            val index = line.indexOf('{')
            val name = line.substring(0, index)
            val rules = line.substr(index + 1, -1).split(',')
            val workflow = mutableListOf<IRule>()

            for (rule in rules) {
                workflow.add(if (rule.contains(':')) Rule0(rule) else Rule1(rule))
            }

            workflows[name] = workflow
        }
    }

    private fun parseRatings(br: BufferedReader) {
        while (true) {
            val line = br.readLine() ?: break
            val split = line.substr(1, -1).split(',')
            val x = split[0].split('=')[1].toInt()
            val m = split[1].split('=')[1].toInt()
            val a = split[2].split('=')[1].toInt()
            val s = split[3].split('=')[1].toInt()
            ratings.add(Rating(x, m, a, s))
        }
    }

    private fun accepted(r: Rating): Boolean {
        var key = "in"

        while (key != "A" && key != "R") {
            key = workflows[key]!!
                .stream()
                .map { it.eval(r) }
                .filter(Objects::nonNull)
                .findFirst()
                .get()
        }

        return key == "A"
    }

    private fun dfs(key: String, bounds: Bounds): Set<Bounds> {
        if (key == "R") {
            return Collections.emptySet()
        }

        if (key == "A") {
            return Collections.singleton(bounds)
        }

        val rules = workflows[key]!!
        val set = mutableSetOf<Bounds>()
        var curr = bounds

        for (rule in rules) {
            if (rule is Rule0) {
                val (left, right) = curr.branch(rule)

                if (left.validate()) {
                    set.addAll(dfs(rule.c, left))
                }

                if (!right.validate()) {
                    break
                }

                curr = right
            } else if (rule is Rule1) {
                set.addAll(dfs(rule.key, curr.copy()))
            }
        }

        return set
    }

    override fun part1(): Int {
        return ratings
            .stream()
            .filter(::accepted)
            .map { it.x + it.m + it.a + it.s }
            .reduce(0) { acc, x -> acc + x }
    }

    override fun part2(): Long {
        val intervals = dfs("in", Bounds(Pair(1, 4000), Pair(1, 4000), Pair(1, 4000),Pair(1, 4000)))
        return intervals.map(Bounds::combos).sum()
    }
}
