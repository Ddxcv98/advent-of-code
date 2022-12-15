package year2022.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(24, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(93, problem.part2())
    }
}
