package util

inline fun <reified T> rotatedCounterClockwise(matrix: Array<Array<T>>): Array<Array<T>> {
    val n = matrix.size
    val m = matrix[0].size

    return Array(m) { i ->
        Array(n) { j ->
            matrix[j][m - i - 1]
        }
    }
}
