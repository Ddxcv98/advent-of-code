package year2023.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(1320, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(145, problem.part2())
    }
}
