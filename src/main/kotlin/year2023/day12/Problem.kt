package year2023.day12

import IProblem

class Problem : IProblem {
    private val springs = mutableListOf<SpringRow>()

    init {
        javaClass
            .getResourceAsStream("/2023/12.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val split = line.split(' ')
                val chars = split[0].toCharArray()
                val damaged = split[1]
                    .split(',')
                    .map(String::toInt)
                    .toIntArray()

                springs.add(SpringRow(chars, damaged))
            }
    }

    override fun part1(): Long {
        return springs.sumOf(SpringRow::arrangements)
    }

    override fun part2(): Long {
        return springs
            .map {
                val chars = CharArray(it.chars.size * 5 + 4) { i ->
                    val mod = i % (it.chars.size + 1)
                    if (mod == it.chars.size) '?' else it.chars[mod]
                }

                val damaged = IntArray(it.damaged.size * 5) { i -> it.damaged[i % it.damaged.size] }

                SpringRow(chars, damaged)
            }
            .sumOf(SpringRow::arrangements)
    }
}
