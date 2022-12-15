package year2022.day03

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(157, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(70, problem.part2())
    }
}
