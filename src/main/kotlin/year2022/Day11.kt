package year2022

import Problem
import java.lang.Exception

private class Monkey(
    private val id: Int,
    startingItems: Collection<ULong>,
    private val operation: (ULong) -> ULong,
    private val test: (ULong) -> Unit
) {
    private val items = ArrayDeque(startingItems)
    var inspections = 0UL

    fun addItem(item: ULong) {
        items.addLast(item)
    }

    fun getItem(): ULong {
        inspections++
        return items.removeFirst()
    }

    fun round(relief: UInt) {
        while (items.isNotEmpty()) {
            val item = operation(getItem()) / relief
            test.invoke(item)
        }
    }
}

class Day11 : Problem {
    private val monkeys = mutableListOf<Monkey>()
    private var divisor = 1UL

    init {
        val br = javaClass
            .getResourceAsStream("/2022/11.txt")!!
            .bufferedReader()

        br.use {
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

    private fun parseTest(test: String, ifTrue: String, ifFalse: String): (ULong) -> Unit {
        val d = test.substring(21).toULong()
        val destTrue = ifTrue.substring(29).toInt()
        val destFalse = ifFalse.substring(30).toInt()

        divisor *= d

        return {
            val dest = if (it % d == 0UL) destTrue else destFalse
            monkeys[dest].addItem(it)
        }
    }

    private fun getBusinessLevel(rounds: Int, relief: UInt): ULong {
        for (i in 0 until rounds) {
            for (monkey in monkeys) {
                monkey.round(relief)
            }
        }

        return monkeys
            .map(Monkey::inspections)
            .sortedDescending()
            .take(2)
            .reduce { a, i -> a * i }
    }

    override fun part1() {
        println(getBusinessLevel(20, 3U))
    }

    override fun part2() {
        println(getBusinessLevel(10000, 1U))
    }
}
