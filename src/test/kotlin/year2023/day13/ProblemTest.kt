package year2023.day13

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(405, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(400, problem.part2())
    }
}
