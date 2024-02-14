package year2023.day23

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(94, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(154, problem.part2())
    }
}
