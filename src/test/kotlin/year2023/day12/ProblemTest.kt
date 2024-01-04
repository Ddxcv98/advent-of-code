package year2023.day12

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(21, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(525152, problem.part2())
    }
}
