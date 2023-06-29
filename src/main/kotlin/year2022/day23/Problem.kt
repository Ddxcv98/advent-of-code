package year2022.day23

import IProblem

class Problem : IProblem {
    private val elves = mutableListOf<Point>()

    private val directions = listOf(
        { p: Point -> listOf(Point(p.x, p.y + 1), Point(p.x + 1, p.y + 1), Point(p.x - 1, p.y + 1)) },
        { p: Point -> listOf(Point(p.x, p.y - 1), Point(p.x + 1, p.y - 1), Point(p.x - 1, p.y - 1)) },
        { p: Point -> listOf(Point(p.x - 1, p.y), Point(p.x - 1, p.y + 1), Point(p.x - 1, p.y - 1)) },
        { p: Point -> listOf(Point(p.x + 1, p.y), Point(p.x + 1, p.y + 1), Point(p.x + 1, p.y - 1)) }
    )

    init {
        val p = Point(0, 0)

        javaClass
            .getResourceAsStream("/2022/23.txt")!!
            .bufferedReader()
            .use {
                val cbuf = CharArray(1)

                while (it.read(cbuf) != -1) {
                    if (cbuf[0] == '\n') {
                        p.x = 0
                        p.y--
                        continue
                    }

                    if (cbuf[0] == '#') {
                        elves.add(p.copy())
                    }

                    p.x++
                }
            }
    }

    private fun hasNeighbors(set: Set<Point>, p: Point): Boolean {
        val q = Point(p.x - 1, p.y + 1)

        while (q.y != p.y - 2) {
            if (q != p && q in set) {
                return true
            }

            if (q.x == p.x + 1) {
                q.x = p.x - 1
                q.y--
            } else {
                q.x++
            }
        }

        return false
    }

    private fun propose(set: Set<Point>, deque: ArrayDeque<(Point) -> List<Point>>, p: Point): Point? {
        if (!hasNeighbors(set, p)) {
            return null
        }

        for (direction in deque) {
            val list = direction(p)

            if (list.none { it in set }) {
                return list[0]
            }
        }

        return null
    }

    private fun move(set: MutableSet<Point>, deque: ArrayDeque<(Point) -> List<Point>>): Int {
        val proposals = mutableMapOf<Point, MutableList<Point>>()
        var c = 0

        for (p in set) {
            val q = propose(set, deque, p)

            if (q != null) {
                proposals
                    .computeIfAbsent(q) { mutableListOf() }
                    .add(p)
            }
        }

        for ((p, list) in proposals) {
            if (list.size == 1) {
                set.remove(list[0])
                set.add(p)
                c++
            }
        }

        deque.addLast(deque.removeFirst())
        return c
    }

    override fun part1(): Int {
        val set = elves.toMutableSet()
        val deque = ArrayDeque(directions)

        for (i in 1..10) {
            move(set, deque)
        }

        val left = set.minOf { it.x }
        val top = set.maxOf { it.y }
        val right = set.maxOf { it.x }
        val bottom = set.minOf { it.y }
        val p = Point(left, top)
        var c = 0

        while (p.y >= bottom) {
            if (p !in set) {
                c++
            }

            if (p.x == right) {
                p.x = left
                p.y--
            } else {
                p.x++
            }
        }

        return c
    }

    override fun part2(): Int {
        val set = elves.toMutableSet()
        val deque = ArrayDeque(directions)
        var c = 0

        do {
            c++
        } while (move(set, deque) != 0)

        return c
    }
}
