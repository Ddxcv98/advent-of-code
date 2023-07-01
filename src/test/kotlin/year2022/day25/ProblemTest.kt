package year2022.day25

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals("2=-1=0", problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(0, problem.part2())
    }
}
