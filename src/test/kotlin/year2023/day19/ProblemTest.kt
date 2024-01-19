package year2023.day19

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(19114, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(167409079868000, problem.part2())
    }
}
