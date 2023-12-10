package year2023.day05

import IProblem
import java.io.BufferedReader
import java.lang.Exception
import java.util.TreeMap
import java.util.regex.Pattern

class Problem : IProblem {
    private val typePattern = Pattern.compile("(\\w+)-to-(\\w+)")
    private val numberPattern = Pattern.compile("\\d+")
    private val seeds: Array<Long>
    private val almanac = mutableMapOf<String, Mapper>()

    init {
        javaClass
            .getResourceAsStream("/2023/05.txt")!!
            .bufferedReader()
            .use { br ->
                seeds = numberPattern
                    .matcher(br.readLine())
                    .results()
                    .map { it.group().toLong() }
                    .toArray { arrayOfNulls(it) }

                br.readLine()

                while (true) {
                    val mapper = parseMap(br) ?: break
                    almanac[mapper.src] = mapper
                }
            }
    }

    private fun parseMap(br: BufferedReader): Mapper? {
        val typeLine = br.readLine() ?: return null
        val typeMatcher = typePattern.matcher(typeLine)

        if (!typeMatcher.find()) {
            throw Exception("bruh moment")
        }

        val mapper = Mapper(typeMatcher.group(1), typeMatcher.group(2))

        while (true) {
            val numberLine = br.readLine()

            if (numberLine == null || numberLine.isEmpty()) {
                break
            }

            val results = numberPattern
                .matcher(numberLine)
                .results()
                .map { it.group().toLong() }
                .toArray { arrayOfNulls<Long>(it) }

            val offset = Offset(results[0]!!, results[1]!!, results[2]!!)
            mapper.offsets[offset.srcStart] = offset
        }

        return mapper
    }

    private fun dfs(almanac: Map<String, Mapper>, src: String, x: Long): Long {
        val mapper = almanac[src] ?: return x
        val y = mapper.map(x)
        return dfs(almanac, mapper.dest, y)
    }

    private fun invertedAlmanac(): Map<String, Mapper> {
        val iAlmanac = mutableMapOf<String, Mapper>()

        for (mapper in almanac.values) {
            val iMapper = mapper.inverted()
            iAlmanac[iMapper.src] = iMapper
        }

        return iAlmanac
    }

    override fun part1(): Long {
        return seeds.minOf { dfs(almanac, "seed", it) }
    }

    override fun part2(): Long {
        val ranges = TreeMap<Long, Long>()
        val inverted = invertedAlmanac()
        var x = 0L

        for (i in 0 until seeds.count() step 2) {
            ranges[seeds[i]] = seeds[i] + seeds[i + 1]
        }

        while (true) {
            val seed = dfs(inverted, "location", x)
            val length = ranges.floorEntry(seed)

            if (length != null && seed < length.value) {
                return x
            }

            x++
        }
    }
}
