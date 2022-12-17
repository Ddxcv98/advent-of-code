package year2022.day16

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(1651, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(1707, problem.part2())
    }
}
