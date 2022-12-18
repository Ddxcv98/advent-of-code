import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime

val mainContent = """package %s

import solve

fun main() {
    solve { Problem() }
}
"""

val problemContent = """package %s

import IProblem

class Problem : IProblem {
    override fun part1() {
        TODO("Not yet implemented")
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}
"""

val testContent = """package %s

import kotlin.test.Test
import kotlin.test.assertEquals

class ProblemTest {
    private val problem = Problem()

    @Test
    fun part1() {
        assertEquals(, problem.part1())
    }

    @Test
    fun part2() {
        assertEquals(, problem.part2())
    }
}
"""

fun parseArgs(flags: MutableMap<String, String>, other: MutableList<String>) {
    var i = 0

    while (i < args.size) {
        if (args[i].startsWith('-')) {
            flags[args[i]] = args[i + 1]
            i += 2
        } else {
            other.add(args[i])
            i++
        }
    }
}

fun main() {
    val flags = mutableMapOf<String, String>()
    val other = mutableListOf<String>()

    parseArgs(flags, other)

    val now = LocalDateTime.now()
    val year = flags.getOrDefault("-year", now.year.toString())
    val day = when (val arg = flags["-day"]) {
        null -> "%02d".format(now.dayOfMonth)
        else -> when (arg.length) {
            1 -> '0' + arg
            else -> arg
        }
    }

    val packageYear = "year" + year
    val packageDay = "day" + day
    val packageName = "${packageYear}.${packageDay}"

    val mainDir = Paths.get("src", "main", "kotlin", packageYear, packageDay)
    val testDir = Paths.get("src", "test", "kotlin", packageYear, packageDay)
    val mainResource = Paths.get("src", "main", "resources", year, day + ".txt")
    val testResource = Paths.get("src", "test", "resources", year, day + ".txt")

    Files.createDirectories(mainDir)
    Files.createDirectories(testDir)
    Files.createDirectories(mainResource.parent)
    Files.createDirectories(testResource.parent)

    Files.writeString(mainDir.resolve("Main.kt"), mainContent.format(packageName))
    Files.writeString(mainDir.resolve("Problem.kt"), problemContent.format(packageName))
    Files.writeString(testDir.resolve("ProblemTest.kt"), testContent.format(packageName))
    Files.createFile(mainResource)
    Files.createFile(testResource)
}

main()
