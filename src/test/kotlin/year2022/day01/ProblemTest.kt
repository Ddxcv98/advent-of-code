package year2022.day01

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(24000, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(45000, problem.part2())
    }
}
