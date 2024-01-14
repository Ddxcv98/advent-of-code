package year2023.day18

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(62, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(952408144115, problem.part2())
    }
}
