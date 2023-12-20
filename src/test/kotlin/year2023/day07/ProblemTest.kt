package year2023.day07

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(6440, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(5905, problem.part2())
    }
}
