package year2023.day07

class Hand(cards: String, bet: Int) : BaseHand(cards, bet) {
    override fun playableCards(): CharArray {
        return chars
    }

    override fun valueOf(c: Char): Int = when (c) {
        '2' -> 1
        '3' -> 2
        '4' -> 3
        '5' -> 4
        '6' -> 5
        '7' -> 6
        '8' -> 7
        '9' -> 8
        'T' -> 9
        'J' -> 10
        'Q' -> 11
        'K' -> 12
        'A' -> 13
        else -> 0
    }
}
