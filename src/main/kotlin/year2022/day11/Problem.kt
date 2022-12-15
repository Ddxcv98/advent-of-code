package year2022.day11

import IProblem
import java.lang.Exception

class Problem : IProblem {
    private val monkeys = mutableListOf<Monkey>()
    private var divisor = 1UL

    init {
        javaClass
            .getResourceAsStream("/2022/11.txt")!!
            .bufferedReader()
            .use {
                while (true) {
                    val line = it.readLine() ?: break
                    if (line.isEmpty()) continue
                    val id = line.substring(7, line.lastIndex).toInt()
                    val items = it.readLine().substring(18).split(", ").map(String::toULong)
                    val operation = parseOperation(it.readLine())
                    val test = parseTest(it.readLine(), it.readLine(), it.readLine())
                    monkeys.add(Monkey(id, items, operation, test))
                }
            }
    }

    private fun parseOperation(s: String): (ULong) -> ULong {
        val split = s.substring(19).split(' ')
        val left = if (split[0] == "old") null else split[0].toULong()
        val right = if (split[2] == "old") null else split[2].toULong()

        return {
            val l = left ?: it
            val r = right ?: it

            when (split[1]) {
                "+" -> (l + r) % divisor
                "*" -> (l * r) % divisor
                else -> throw Exception("bruh moment")
            }
        }
    }

    private fun parseTest(test: String, ifTrue: String, ifFalse: String): (ULong) -> Int {
        val d = test.substring(21).toULong()
        val destTrue = ifTrue.substring(29).toInt()
        val destFalse = ifFalse.substring(30).toInt()

        divisor *= d

        return { if (it % d == 0UL) destTrue else destFalse }
    }

    private fun getBusinessLevel(rounds: Int, relief: UInt): ULong {
        val copy = Array(monkeys.size) { monkeys[it].copy() }

        for (i in 0 until rounds) {
            for (monkey in copy) {
                monkey.round(copy, relief)
            }
        }

        return copy
            .map(Monkey::inspections)
            .sortedDescending()
            .take(2)
            .reduce { a, i -> a * i }
    }

    override fun part1(): ULong {
        return getBusinessLevel(20, 3U)
    }

    override fun part2(): ULong {
        return getBusinessLevel(10000, 1U)
    }
}
