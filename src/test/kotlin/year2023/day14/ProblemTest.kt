package year2023.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(136, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(64, problem.part2())
    }
}
