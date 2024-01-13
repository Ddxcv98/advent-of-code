package year2023.day17

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(102, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(94, problem.part2())
    }
}
