package year2022.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(10605UL, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(2713310158UL, problem.part2())
    }
}
