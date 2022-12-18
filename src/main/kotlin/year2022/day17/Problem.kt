package year2022.day17

import IProblem
import kotlin.math.max
import kotlin.math.min

class Problem : IProblem {
    private val jets = javaClass
        .getResource("/2022/17.txt")!!
        .readText()
        .trim()
        .toCharArray()

    private fun cycleSize(matrix: Array<BooleanArray>, i: Int, j: Int): Int {
        var i = i
        var j = j
        var n = 0

        while (j != matrix.size && matrix[i].contentEquals(matrix[j])) {
            i++
            j++
            n++
        }

        return n
    }

    private fun calculateHeight(
        pieceHeight: Map<Int, Int>,
        stats: Stats,
        totalPieces: Long,
        pieceNumber: Int,
        height: Int,
        heightPerCycle: Int
    ): Long {
        val piecesLeft = totalPieces - pieceNumber - 1
        val piecesPerCycle = pieceNumber - stats.pieces + 1
        val cyclesLeft = piecesLeft / piecesPerCycle
        val remainder = piecesLeft % piecesPerCycle
        val cyclesHeight = cyclesLeft * heightPerCycle
        val remainderPiece = (stats.pieces + remainder - 1).toInt()
        val remainderHeight = pieceHeight[remainderPiece]!! - stats.height
        return height + cyclesHeight + remainderHeight + 1
    }

    private fun towerSize(n: Long): Long {
        val maxComputedPieces = min(n, 5000).toInt()
        val size = PIECES.sumOf { it.height() + 1 } * (maxComputedPieces + 1) / PIECES.size + OFFSET_Y
        val matrix = Array(size) { BooleanArray(WIDTH) }
        val pieceHeight = mutableMapOf<Int, Int>()
        val state = mutableMapOf<Indexes, Stats>()
        var height = -1
        var jetIndex = 0

        for (i in 0 until maxComputedPieces) {
            val pieceIndex = (i % PIECES.size)
            val piece = PIECES[pieceIndex].copy()

            piece.move(OFFSET_X, height + OFFSET_Y + 1)

            do {
                when (jets[jetIndex]) {
                    '<' -> piece.shiftLeft(matrix)
                    else -> piece.shiftRight(matrix)
                }

                piece.shiftDown(matrix)
                jetIndex = (jetIndex + 1) % jets.size
            } while (!piece.grounded)

            val indexes = Indexes(pieceIndex, jetIndex)
            val stats = state[indexes]

            piece.rocks.forEach { matrix[size - it.y - 1][it.x] = true }
            height = max(piece.height(), height)
            pieceHeight[i] = height
            state[indexes] = Stats(height, i + 1)

            if (stats == null) {
                continue
            }

            val heightPerCycle = cycleSize(matrix, matrix.size - height - 1, matrix.size - stats.height - 1)

            if (heightPerCycle == height - stats.height) {
                return calculateHeight(pieceHeight, stats, n, i, height, heightPerCycle)
            }
        }

        return height + 1L
    }

    override fun part1(): Long {
        return towerSize(2022)
    }

    override fun part2(): Long {
        return towerSize(1_000_000_000_000)
    }
}
