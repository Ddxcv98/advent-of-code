package year2022.day07

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(95437UL, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(24933642UL, problem.part2())
    }
}
