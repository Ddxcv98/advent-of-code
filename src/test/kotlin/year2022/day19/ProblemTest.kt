package year2022.day19

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(33, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(3472, problem.part2())
    }
}
