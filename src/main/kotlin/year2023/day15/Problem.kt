package year2023.day15

import IProblem

class Problem : IProblem {
    private val strings = javaClass
        .getResourceAsStream("/2023/15.txt")!!
        .bufferedReader()
        .readText()
        .trim()
        .split(',')

    private val boxes = Array(256) { LinkedHashMap<String, Int>() }

    private fun hash(s: String): Int {
        var h = 0

        for (c in s) {
            h += c.code
            h *= 17
            h %= 256
        }

        return h
    }

    private fun hashmap(s: String) {
        val split = s.split('-', '=')
        val label = split[0]
        val focln = if (split[1].isEmpty()) 0 else split[1].toInt()
        val box = boxes[hash(label)]

        if (focln == 0) {
            box.remove(label)
        } else {
            box[label] = focln
        }
    }

    override fun part1(): Int {
        return strings.sumOf(::hash)
    }

    override fun part2(): Int {
        for (s in strings) {
            hashmap(s)
        }

        var fp = 0

        for (i in boxes.indices) {
            val box = boxes[i]

            if (box.isEmpty()) {
                continue
            }

            var j = 0

            for (lens in box.values) {
                fp += (i + 1) * (++j) * lens
            }
        }

        return fp
    }
}
