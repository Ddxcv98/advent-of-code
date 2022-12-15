import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun solve(troubleMaker: () -> IProblem) {
    val (problem, t0) = measureTimedValue(troubleMaker)
    val (res1, t1) = measureTimedValue(problem::part1)
    val (res2, t2) = measureTimedValue(problem::part2)

    println("""
        Part 1
        $res1

        Part 2
        $res2

        Parsing/Precomputation...: ${t0.toString(DurationUnit.MILLISECONDS, 2)}
        Part 1...................: ${t1.toString(DurationUnit.MILLISECONDS, 2)}
        Part 2...................: ${t2.toString(DurationUnit.MILLISECONDS, 2)}
        Total....................: ${(t0 + t1 + t2).toString(DurationUnit.MILLISECONDS, 2)}
    """.trimIndent())
}
