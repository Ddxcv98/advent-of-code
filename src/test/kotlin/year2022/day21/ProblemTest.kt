package year2022.day21

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(152, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(301, problem.part2())
    }
}
