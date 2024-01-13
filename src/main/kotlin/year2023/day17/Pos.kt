package year2023.day17

data class Pos(val x: Int, val y: Int, val size: Int, val dir: Char) {
    fun walk() = sequence {
        when (dir) {
            '<' -> {
                yield(Pos(x - 1, y, size + 1, '<'))
                yield(Pos(x, y - 1, 1, '^'))
                yield(Pos(x, y + 1, 1, 'v'))
            }
            '^' -> {
                yield(Pos(x, y - 1, size + 1, '^'))
                yield(Pos(x - 1, y, 1, '<'))
                yield(Pos(x + 1, y, 1, '>'))
            }
            '>', -> {
                yield(Pos(x + 1, y, size + 1, '>'))
                yield(Pos(x, y - 1, 1, '^'))
                yield(Pos(x, y + 1, 1, 'v'))
            }
            'v' -> {
                yield(Pos(x, y + 1, size + 1, 'v'))
                yield(Pos(x - 1, y, 1, '<'))
                yield(Pos(x + 1, y, 1, '>'))
            }
        }
    }
}
