package year2022.day22

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem(
        arrayOf(
            arrayOf( // 0
                Pair(5, 2),
                Pair(3, 1),
                Pair(2, 1),
                Pair(1, 1)
            ),
            arrayOf( // 1
                Pair(2, 0),
                Pair(4, 3),
                Pair(5, 3),
                Pair(0, 1)
            ),
            arrayOf( // 2
                Pair(3, 0),
                Pair(4, 0),
                Pair(1, 2),
                Pair(0, 0)
            ),
            arrayOf( // 3
                Pair(5, 1),
                Pair(4, 1),
                Pair(2, 2),
                Pair(0, 3)
            ),arrayOf( // 4
                Pair(5, 0),
                Pair(1, 3),
                Pair(2, 3),
                Pair(3, 3)
            ),
            arrayOf( // 5
                Pair(0, 2),
                Pair(1, 0),
                Pair(4, 2),
                Pair(3, 2)
            )
        )
    )

    @Test
    fun part1() {
        assertEquals(6032, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(5031, problem.part2())
    }
}
