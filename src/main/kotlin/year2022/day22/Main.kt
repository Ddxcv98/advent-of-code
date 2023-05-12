package year2022.day22

import solve

fun main() {
    solve {
        Problem(
            arrayOf(
                arrayOf( // 0
                    Pair(1, 0),
                    Pair(2, 1),
                    Pair(3, 0),
                    Pair(5, 0)
                ),
                arrayOf( // 1
                    Pair(4, 2),
                    Pair(2, 2),
                    Pair(0, 2),
                    Pair(5, 3)
                ),
                arrayOf( // 2
                    Pair(1, 3),
                    Pair(4, 1),
                    Pair(3, 1),
                    Pair(0, 3)
                ),
                arrayOf( // 3
                    Pair(4, 0),
                    Pair(5, 1),
                    Pair(0, 0),
                    Pair(2, 0)
                ), arrayOf( // 4
                    Pair(1, 2),
                    Pair(5, 2),
                    Pair(3, 2),
                    Pair(2, 3)
                ),
                arrayOf( // 5
                    Pair(4, 3),
                    Pair(1, 1),
                    Pair(0, 1),
                    Pair(3, 3)
                )
            )
        )
    }
}
