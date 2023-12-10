package year2023.day04

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(13, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(30, problem.part2())
    }
}
