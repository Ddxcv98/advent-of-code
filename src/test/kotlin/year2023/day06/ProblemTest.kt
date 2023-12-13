package year2023.day06

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(288, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(71503, problem.part2())
    }
}
