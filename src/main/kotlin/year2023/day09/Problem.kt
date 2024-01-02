package year2023.day09

import IProblem

class Problem : IProblem {
    private val nums = mutableListOf<ArrayDeque<Int>>()

    init {
        javaClass
            .getResourceAsStream("/2023/09.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val split = line.split(' ').map(String::toInt)
                nums.add(ArrayDeque(split))
            }

        extrapolate()
    }

    private fun extrapolate() {
        for (i in nums.indices) {
            val matrix = mutableListOf<ArrayDeque<Int>>()
            val seq = ArrayDeque(nums[i])
            matrix.add(seq)

            while (matrix.last().any { it != 0 }) {
                val curr = matrix.last()
                val next = ArrayDeque<Int>()

                for (j in 0 until curr.size - 1) {
                    next.add(curr[j + 1] - curr[j])
                }

                matrix.add(next)
            }

            for (j in matrix.size - 3 downTo 0) {
                val curr = matrix[j]
                val next = matrix[j + 1]
                curr.addFirst(curr.first() - next.first())
                curr.addLast(curr.last() + next.last())
            }

            nums[i].addFirst(seq.first())
            nums[i].addLast(seq.last())
        }
    }

    override fun part1(): Int {
        return nums.fold(0) { acc, list -> acc + list.last() }
    }

    override fun part2(): Int {
        return nums.fold(0) { acc, list -> acc + list.first() }
    }
}
