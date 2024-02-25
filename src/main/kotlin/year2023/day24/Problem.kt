package year2023.day24

import IProblem
import java.util.regex.Pattern

class Problem(private val lower: Double = 200000000000000.0, private val upper: Double = 400000000000000.0) : IProblem {
    private val hailstones = javaClass
        .getResourceAsStream("/2023/24.txt")!!
        .bufferedReader()
        .lines()
        .map {
            val split0 = it.split(" @ ")
            val pos = split0[0].split(", ").map(String::toDouble)
            val vel = split0[1].split(", ").map(String::toDouble)
            Hailstone(pos[0], pos[1], pos[2], vel[0], vel[1], vel[2])
        }
        .toList()

    override fun part1(): Int {
        var count = 0

        for (i in hailstones.indices) {
            val h1 = hailstones[i]

            for (j in i + 1 until hailstones.size) {
                val h2 = hailstones[j]
                val p = h1.intersection(h2) ?: continue
                val (x, y) = p

                if (x in lower..upper && y in lower..upper) {
                    count++
                }
            }
        }

        return count
    }

    override fun part2(): Long {
        // sigh... no, I am not going to write my own gaussian elimination algorithm
        val equations = mutableListOf<String>()

        for (i in 0 until 3) {
            val h = hailstones[i]
            val x = h.x.toLong()
            val y = h.y.toLong()
            val z = h.z.toLong()
            val vx = h.vx.toLong()
            val vy = h.vy.toLong()
            val vz = h.vz.toLong()
            val t = i.plus(97).toChar()

            equations.add("$x+$vx*$t=x+vx*$t")
            equations.add("$y+$vy*$t=y+vy*$t")
            equations.add("$z+$vz*$t=z+vz*$t")
        }

        val process = ProcessBuilder("maxima").start()

        process
            .outputStream
            .bufferedWriter()
            .use { it.write("solve(${equations}, [a,b,c,vx,vy,vz,x,y,z]);\n") }

        process.waitFor()

        val out = StringBuilder()

        process.inputStream
            .bufferedReader()
            .use { br ->
                var found = false

                while (true) {
                    val line = br.readLine()

                    if (line == null || found && line.startsWith("(%i2)")) {
                        break
                    }

                    if (line.startsWith("(%o1)")) {
                        out.append(line.substring(6))
                        found = true
                    } else if (found) {
                        out.append(line)
                    }
                }
            }

        val pattern = Pattern.compile("\\w+ = (\\d+)")

        val (x, y, z) = pattern
            .matcher(out)
            .results()
            .skip(5)
            .map { it.group(1).toLong() }
            .toList()

        return x + y + z
    }
}
