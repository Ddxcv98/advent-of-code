package year2023.day19

data class Bounds(var x: Pair<Int, Int>, var m: Pair<Int, Int>, var a: Pair<Int, Int>, var s: Pair<Int, Int>) {
    fun branch(rule: Rule0): Pair<Bounds, Bounds> {
        val prop = when (rule.a) {
            'x' -> Bounds::x
            'm' -> Bounds::m
            'a' -> Bounds::a
            else -> Bounds::s
        }

        val (lower, upper) = prop.get(this)
        val left = copy()
        val right = copy()

        when (rule.operator) {
            '<' -> {
                prop.set(left, Pair(lower, rule.b - 1))
                prop.set(right, Pair(rule.b, upper))
            }
            else -> {
                prop.set(left, Pair(rule.b + 1, upper))
                prop.set(right, Pair(lower, rule.b))
            }
        }

        return Pair(left, right)
    }

    fun combos(): Long {
        return (x.second - x.first + 1).toLong() *
                (m.second - m.first + 1) *
                (a.second - a.first + 1) *
                (s.second - s.first + 1)
    }

    fun validate(): Boolean {
        return x.first <= x.second &&
                m.first <= m.second &&
                a.first <= a.second &&
                s.first <= s.second
    }
}
