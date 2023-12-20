package year2023.day07

class WildHand(cards: String, bet: Int) : BaseHand(cards, bet) {
    override fun playableCards(): CharArray {
        val freq = IntArray(128)
        var max = 'X'
        var count = 0

        for (char in chars) {
            if (char == 'J') {
                continue
            }

            if (++freq[char.code] > count) {
                max = char
                count = freq[char.code]
            }
        }

        val wild = CharArray(chars.size) {
            if (chars[it] == 'J') {
                max
            } else {
                chars[it]
            }
        }

        return wild
    }

    override fun valueOf(c: Char): Int = when (c) {
        'J' -> 1
        '2' -> 2
        '3' -> 3
        '4' -> 4
        '5' -> 5
        '6' -> 6
        '7' -> 7
        '8' -> 8
        '9' -> 9
        'T' -> 10
        'Q' -> 11
        'K' -> 12
        'A' -> 13
        else -> 0
    }
}
