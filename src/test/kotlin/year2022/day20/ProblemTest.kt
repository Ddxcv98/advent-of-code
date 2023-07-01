package year2022.day20

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(3, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(1623178306, problem.part2())
    }
}
