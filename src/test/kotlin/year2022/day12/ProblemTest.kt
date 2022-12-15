package year2022.day12

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(31, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(29, problem.part2())
    }
}
