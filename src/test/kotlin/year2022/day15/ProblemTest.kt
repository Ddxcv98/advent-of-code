package year2022.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem(10, 20)

    @Test
    fun part1() {
        assertEquals(26, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(56000011UL, problem.part2())
    }
}
