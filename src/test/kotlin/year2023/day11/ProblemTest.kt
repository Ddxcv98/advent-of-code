package year2023.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(374, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(82000210, problem.part2())
    }
}
