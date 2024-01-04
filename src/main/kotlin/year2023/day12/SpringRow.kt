package year2023.day12

class SpringRow(val chars: CharArray, val damaged: IntArray) {
    private val cache = HashMap<Pair<Int, Int>, Long>()

    private fun match(index: Int, length: Int): Boolean {
        if (index > 0 && chars[index - 1] == '#') {
            return false
        }

        if (index + length < chars.size && chars[index + length] == '#') {
            return false
        }

        for (i in 0 until length) {
            if (chars[index + i] == '.') {
                return false
            }
        }

        return true
    }

    private fun arrangements(i: Int, j: Int): Long {
        val pair = Pair(i, j)
        val mem = cache[pair]

        if (mem != null) {
            return mem
        }

        if (i >= chars.size) {
            return if (j == damaged.size) 1 else 0
        }

        if (j == damaged.size) {
            for (k in i until chars.size) {
                if (chars[k] == '#') {
                    return 0
                }
            }

            return 1
        }

        val d = damaged[j]
        var n = 0L

        for (k in i..chars.size - d) {
            if (match(k, d)) {
                n += arrangements(k + d + 1, j + 1)
            }

            if (chars[k] == '#') {
                break
            }
        }

        cache[pair] = n
        return n
    }

    fun arrangements(): Long {
        return arrangements(0, 0)
    }
}
