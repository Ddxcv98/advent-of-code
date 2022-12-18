package year2022.day17

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(3068, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(1514285714288, problem.part2())
    }
}
