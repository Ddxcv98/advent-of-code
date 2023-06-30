package year2022.day24

abstract class Blizzard(var x: Int, var y: Int) {
    companion object {
        fun of(x: Int, y: Int, c: Char): Blizzard {
            return when (c) {
                '^' -> UpBlizzard(x, y)
                'v' -> DownBlizzard(x, y)
                '<' -> LeftBlizzard(x, y)
                '>' -> RightBlizzard(x, y)
                else -> throw Exception("bruh moment")
            }
        }
    }

    abstract fun copy(): Blizzard

    protected abstract fun blizzDeezNuts(matrix: Array<IntArray>)

    fun move(matrix: Array<IntArray>) {
        matrix[y][x]--
        blizzDeezNuts(matrix)
        matrix[y][x]++
    }
}

class UpBlizzard(x: Int, y: Int) : Blizzard(x, y) {
    override fun copy(): Blizzard {
        return UpBlizzard(x, y)
    }

    override fun blizzDeezNuts(matrix: Array<IntArray>) {
        y = if (matrix[y - 1][x] == -1) {
            matrix.size - 2
        } else {
            y - 1
        }
    }

    override fun toString(): String {
        return "^"
    }
}

class DownBlizzard(x: Int, y: Int) : Blizzard(x, y) {
    override fun copy(): Blizzard {
        return DownBlizzard(x, y)
    }

    override fun blizzDeezNuts(matrix: Array<IntArray>) {
        y = if (matrix[y + 1][x] == -1) {
            1
        } else {
            y + 1
        }
    }

    override fun toString(): String {
        return "v"
    }
}

class LeftBlizzard(x: Int, y: Int) : Blizzard(x, y) {
    override fun copy(): Blizzard {
        return LeftBlizzard(x, y)
    }

    override fun blizzDeezNuts(matrix: Array<IntArray>) {
        x = if (matrix[y][x - 1] == -1) {
            matrix.first().size - 2
        } else {
            x - 1
        }
    }

    override fun toString(): String {
        return "<"
    }
}

class RightBlizzard(x: Int, y: Int) : Blizzard(x, y) {
    override fun copy(): Blizzard {
        return RightBlizzard(x, y)
    }

    override fun blizzDeezNuts(matrix: Array<IntArray>) {
        x = if (matrix[y][x + 1] == -1) {
            1
        } else {
            x + 1
        }
    }

    override fun toString(): String {
        return ">"
    }
}
