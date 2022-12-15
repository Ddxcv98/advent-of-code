package year2022.day05

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals("CMZ", problem.part1())
    }

    @Test
    fun part2() {
        assertEquals("MCD", problem.part2())
    }
}
