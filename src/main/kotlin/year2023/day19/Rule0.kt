package year2023.day19

class Rule0(s: String) : IRule {
    val a: Char
    val b: Int
    var c: String
    val operator: Char

    private val predicate: (Rating) -> Boolean

    init {
        val index = s.indexOf(':')
        a = s[0]
        b = s.substring(2, index).toInt()
        c = s.substring(index + 1)
        operator = s[1]

        val prop = when (a) {
            'x' -> Rating::x
            'm' -> Rating::m
            'a' -> Rating::a
            else -> Rating::s
        }

        predicate = when (operator) {
            '<' -> { r: Rating -> prop.get(r) < b }
            else -> { r: Rating -> prop.get(r) > b }
        }
    }

    override fun eval(r: Rating) = if (predicate(r)) c else null
}
