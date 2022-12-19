package year2022.day18

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(64, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(58, problem.part2())
    }
}
