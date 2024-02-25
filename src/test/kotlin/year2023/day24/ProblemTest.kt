package year2023.day24

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem(7.0, 27.0)

    @Test
    fun part1() {
        assertEquals(2, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(47, problem.part2())
    }
}
