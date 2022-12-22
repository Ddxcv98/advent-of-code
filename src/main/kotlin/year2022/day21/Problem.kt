package year2022.day21

import IProblem
import java.util.regex.MatchResult
import java.util.regex.Pattern

class Problem : IProblem {
    private val monkeys = mutableMapOf<String, String>()

    init {
        javaClass
            .getResourceAsStream("/2022/21.txt")!!
            .bufferedReader()
            .forEachLine {
                val split = it.split(": ")
                val monkey = split[0]
                val expression = split[1]
                monkeys[monkey] = expression
            }
    }

    private fun calculate(monkey: String): Long {
        val pattern = Pattern.compile("([a-z]+) ([*+\\-/]) ([a-z]+)")
        val expressions = mutableMapOf<String, () -> Long>()

        for ((variable, expression) in monkeys) {
            val matcher = pattern.matcher(expression)

            expressions[variable] = if (matcher.find()) {
                val a = matcher.group(1)
                val operator = matcher.group(2)
                val b = matcher.group(3)

                when (operator) {
                    "*" -> {
                        { expressions[a]!!() * expressions[b]!!() }
                    }

                    "+" -> {
                        { expressions[a]!!() + expressions[b]!!() }
                    }

                    "-" -> {
                        { expressions[a]!!() - expressions[b]!!() }
                    }

                    else -> {
                        { expressions[a]!!() / expressions[b]!!() }
                    }
                }
            } else {
                { expression.toLong() }
            }
        }

        return expressions[monkey]!!()
    }

    private fun expand(expression: String): String {
        if (expression.matches("^\\d+$".toRegex())) {
            return expression
        }

        val vars = Pattern.compile("[a-z]{4}")
            .matcher(expression)
            .results()
            .map(MatchResult::group)
            .toList()

        var expression = expression

        for (variable in vars) {
            val expanded = when (variable) {
                "humn" -> "x"
                else -> expand(monkeys[variable]!!)
            }

            expression = expression.replace(variable, "($expanded)")
        }

        return expression
    }

    override fun part1(): Long {
        return calculate("root")
    }

    /**
     * Yo, for real? What are they thinking?
     * I'm not going to do my own equation solver or whatever I'm supposed to do.
     * I'm putting this into a CAS and getting the result.
     */
    override fun part2(): Long {
        val (a, _, b) = monkeys["root"]!!.split(' ')

        val script = """
            from sympy import *
            x = symbols('x')
            print(int(solve(${expand(a)} - ${expand(b)}, x)[0]))
        """.trimIndent()

        return ProcessBuilder("python", "-c", script)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .start()
            .inputReader()
            .readText()
            .trim()
            .toLong()
    }
}
