package year2023.day05

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(35, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(46, problem.part2())
    }
}
