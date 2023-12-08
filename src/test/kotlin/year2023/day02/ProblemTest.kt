package year2023.day02

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(8, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(2286, problem.part2())
    }
}
