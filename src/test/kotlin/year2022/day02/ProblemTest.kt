package year2022.day02

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(15, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(12, problem.part2())
    }
}
