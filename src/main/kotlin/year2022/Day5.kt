package year2022

import Problem
import java.io.BufferedReader
import java.io.Reader
import java.lang.Exception
import java.util.regex.Pattern

class Day5 : Problem {
    private val matrix = ArrayList<ArrayDeque<Char>>()
    private val actions = ArrayList<Triple<Int, Int, Int>>()

    init {
        val input = javaClass
            .getResource("/2022/5.txt")!!
            .openStream()
            .bufferedReader()

        parseMatrix(input)
        parseActions(input)
        input.close()
    }

    private fun parseMatrix(input: Reader) {
        val cbuf = CharArray(4)
        var col = 0
        input.skip(1)

        while (true) {
            input.read(cbuf, 0, 4)
            val char = cbuf[0]

            if (char.isDigit()) {
                break
            }

            if (col == matrix.size) {
                matrix.add(ArrayDeque())
            }

            if (char.isLetter()) {
                matrix[col].addFirst(char)
            }

            col = when (cbuf[2]) {
                '\n' -> 0
                else -> col + 1
            }
        }

        input.skip(matrix.size.toLong() * 4 - 5)
    }

    private fun parseActions(input: BufferedReader) {
        val pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)")

        while (true) {
            val line = input.readLine() ?: break
            val matcher = pattern.matcher(line)

            if (!matcher.find()) {
                throw Exception("Could not match pattern")
            }

            actions.add(Triple(matcher.group(1).toInt(), matcher.group(2).toInt(), matcher.group(3).toInt()))
        }

    }

    private fun doAction(predicate: (Int, ArrayDeque<Char>, ArrayDeque<Char>) -> Unit) {
        for (action in actions) {
            val source = matrix[action.second - 1]
            val target = matrix[action.third - 1]
            predicate.invoke(action.first, source, target)
        }

        println(matrix.map { it.last() }.joinToString(""))
    }

    override fun part1() {
        doAction { action, source, target ->
            for (i in 0 until action) {
                target.addLast(source.removeLast())
            }
        }
    }

    override fun part2() {
        doAction { action, source, target ->
            val iterator = source.listIterator(source.size - action)

            while (iterator.hasNext()) {
                target.addLast(iterator.next())
            }

            for (i in 0 until action) {
                source.removeLast()
            }
        }
    }
}
