package year2023.day04

import IProblem
import java.util.stream.Collectors

class Problem : IProblem {
    private val cards = mutableListOf<Card>()

    init {
        javaClass
            .getResourceAsStream("/2023/04.txt")!!
            .bufferedReader()
            .forEachLine {
                val split0 = it.split(Regex(":\\s+"))
                val card = Card(split0[0].split(Regex("\\s+"))[1].toInt())
                val split1 = split0[1].split(Regex("\\s+\\|\\s+"))

                for (s in split1[0].split(Regex("\\s+"))) {
                    card.winning.add(s.toInt())
                }

                for (s in split1[1].split(Regex("\\s+"))) {
                    card.owned.add(s.toInt())
                }

                cards.add(card)
            }
    }

    override fun part1(): Int {
        return cards.sumOf(Card::points)
    }

    override fun part2(): Int {
        val matches = cards.stream().collect(Collectors.toMap(Card::id, Card::matches))
        val queue = ArrayDeque(cards.map(Card::id))
        var count = 0

        while (queue.isNotEmpty()) {
            val card = queue.removeFirst()

            for (i in 1..matches[card]!!) {
                queue.add(card + i)
            }

            count++
        }

        return count
    }
}
