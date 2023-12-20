package year2023.day07

import IProblem

class Problem : IProblem {
    private val hands = mutableListOf<Pair<String, Int>>()

    init {
        javaClass
            .getResourceAsStream("/2023/07.txt")!!
            .bufferedReader()
            .forEachLine { line ->
                val split = line.split(' ')
                val cards = split[0]
                val bet = split[1].toInt()

                hands.add(Pair(cards, bet))
            }
    }

    private fun winnings(hands: Iterable<BaseHand>) =
        hands.foldIndexed(0) { i, acc, hand -> acc + hand.bet * (i + 1) }

    override fun part1(): Int {
        val list = hands
            .stream()
            .map { Hand(it.first, it.second) }
            .sorted()
            .toList()

        return winnings(list)
    }

    override fun part2(): Int {
        val list = hands
            .stream()
            .map { WildHand(it.first, it.second) }
            .sorted()
            .toList()

        return winnings(list)
    }
}
