package year2022.day05

import IProblem
import java.io.BufferedReader
import java.io.Reader
import java.lang.Exception
import java.util.regex.Pattern

class Problem : IProblem {
    private val matrix = mutableListOf<ArrayDeque<Char>>()
    private val moves = mutableListOf<Triple<Int, Int, Int>>()

    init {
        javaClass
            .getResourceAsStream("/2022/05.txt")!!
            .bufferedReader()
            .use {
                parseMatrix(it)
                parseMoves(it)
            }
    }

    private fun parseMatrix(reader: Reader) {
        val cbuf = CharArray(4)
        var col = 0
        reader.skip(1)

        while (true) {
            reader.read(cbuf, 0, 4)
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

        reader.skip(matrix.size.toLong() * 4 - 5)
    }

    private fun parseMoves(br: BufferedReader) {
        val pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)")

        br.forEachLine {
            val matcher = pattern.matcher(it)

            if (!matcher.find()) {
                throw Exception("bruh moment")
            }

            moves.add(Triple(matcher.group(1).toInt(), matcher.group(2).toInt(), matcher.group(3).toInt()))
        }
    }

    private fun forEachMove(matrix: Array<ArrayDeque<Char>>, action: (Int, ArrayDeque<Char>, ArrayDeque<Char>) -> Unit) {
        for (move in moves) {
            val source = matrix[move.second - 1]
            val target = matrix[move.third - 1]
            action(move.first, source, target)
        }
    }

    override fun part1(): String {
        val copy = Array(matrix.size) { ArrayDeque(matrix[it]) }

        forEachMove(copy) { n, source, target ->
            for (i in 0 until n) {
                target.addLast(source.removeLast())
            }
        }

        return copy
            .map { it.last() }
            .joinToString("")
    }

    override fun part2(): String {
        val copy = Array(matrix.size) { ArrayDeque(matrix[it]) }

        forEachMove(copy) { n, source, target ->
            val iterator = source.listIterator(source.size - n)

            while (iterator.hasNext()) {
                target.addLast(iterator.next())
            }

            for (i in 0 until n) {
                source.removeLast()
            }
        }

        return copy
            .map { it.last() }
            .joinToString("")
    }
}
