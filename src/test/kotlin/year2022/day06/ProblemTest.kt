package year2022.day06

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(7, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(19, problem.part2())
    }
}
