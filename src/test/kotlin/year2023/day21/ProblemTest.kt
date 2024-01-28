package year2023.day21

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem(6)

    @Test
    fun part1() {
        assertEquals(16, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(528192461129799, problem.part2())
    }
}
