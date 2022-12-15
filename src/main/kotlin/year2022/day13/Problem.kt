package year2022.day13

import IProblem

class Problem : IProblem {
    private val lines = javaClass
        .getResource("/2022/13.txt")!!
        .readText()
        .lines()
        .filter(String::isNotEmpty)
        .map { parseList(it.toCharArray()) }

    private fun getClosingBracket(chars: CharArray, i: Int): Int {
        var brackets = 1
        var j = i + 1

        while (brackets != 0) {
            if (chars[j] == '[') {
                brackets++
            } else if (chars[j] == ']') {
                brackets--
            }

            j++
        }

        return j
    }

    private fun parseList(chars: CharArray): List<Any> {
        if (chars.size == 2) {
            return emptyList()
        }

        val list = mutableListOf<Any>()
        var n = 0
        var i = 1

        while (i < chars.size) {
            when (val c = chars[i]) {
                '[' -> {
                    val j = getClosingBracket(chars, i)
                    list.add(parseList(chars.sliceArray(i until j)))
                    i = j
                }
                ',', ']' -> {
                    list.add(n)
                    n = 0
                }
                else -> {
                    n = n * 10 + c.digitToInt()
                }
            }

            i++
        }

        return list
    }

    private fun compare(a: Any, b: Any): Int {
        if (a is Int && b is Int) {
            return a.compareTo(b)
        }

        val l1 = if (a is List<*>) a else listOf(a)
        val l2 = if (b is List<*>) b else listOf(b)
        var i = 0
        var j = 0

        while (i < l1.size && j < l2.size) {
            val diff = compare(l1[i]!!, l2[j]!!)
            if (diff != 0) return diff
            i++
            j++
        }

        return l1.size.compareTo(l2.size)
    }

    override fun part1(): Int {
        return lines
            .chunked(2)
            .mapIndexed { i, (a, b) -> if (compare(a, b) < 0) i + 1 else 0 }
            .sum()
    }

    override fun part2(): Int {
        val list = lines.toMutableList()
        val key1 = listOf(listOf(2))
        val key2 = listOf(listOf(6))

        list.add(key1)
        list.add(key2)
        list.sortWith { a, b -> compare(a, b) }

        return list.indexOf(key1).plus(1) * list.indexOf(key2).plus(1)
    }
}
