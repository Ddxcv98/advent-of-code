package year2022.day22

interface CubeWalker<P : Pos2D> {
    fun walk(pos: P, steps: Int)
    fun rotate(pos: P, r: Char)
    fun toPos2D(pos: P): Pos2D
}
