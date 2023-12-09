package year2023.day03

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(4361, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(467835, problem.part2())
    }
}
