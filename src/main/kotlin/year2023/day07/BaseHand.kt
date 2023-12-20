package year2023.day07

abstract class BaseHand(val cards: String, val bet: Int) : Comparable<BaseHand> {
    companion object {
        private val comparator = Comparator
            .comparingInt(BaseHand::strength)
            .thenComparing(BaseHand::tiebreaker)
    }

    protected val chars = cards.toCharArray()

    val strength by lazy { calcStrength() }
    val tiebreaker by lazy { calcTiebreaker() }

    private fun calcStrength(): Int {
        val chars = playableCards()
        val freq = IntArray(128)
        var pair = false
        var triple = false

        for (char in chars) {
            freq[char.code]++
        }

        for (count in freq) {
            when (count) {
                2 -> {
                    if (triple) {
                        return FULL_HOUSE
                    }

                    if (pair) {
                        return TWO_PAIR
                    }

                    pair = true
                }
                3 -> {
                    if (pair) {
                        return FULL_HOUSE
                    }

                    triple = true
                }
                4 -> return FOUR_OF_A_KIND
                5 -> return FIVE_OF_A_KIND
            }
        }

        if (triple) {
            return THREE_OF_A_KIND
        }

        if (pair) {
            return PAIR
        }

        return HIGH_CARD
    }

    private fun calcTiebreaker(): Int {
        var n = 0
        var e = 1

        for (i in chars.indices.reversed()) {
            n += e * valueOf(chars[i])
            e *= 13
        }

        return n
    }

    protected abstract fun playableCards(): CharArray

    protected abstract fun valueOf(c: Char): Int

    override fun compareTo(other: BaseHand): Int {
        return comparator.compare(this, other)
    }
}
