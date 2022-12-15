package year2022.day11

class Monkey(
    private val id: Int,
    startingItems: Collection<ULong>,
    private val operation: (ULong) -> ULong,
    private val test: (ULong) -> Int
) {
    private val items = ArrayDeque(startingItems)
    var inspections = 0UL

    fun round(monkeys: Array<Monkey>, relief: UInt) {
        while (items.isNotEmpty()) {
            val item = operation(items.removeFirst()) / relief
            val dest = test(item)

            monkeys[dest].items.addLast(item)
            inspections++
        }
    }

    fun copy(): Monkey {
        return Monkey(id, items, operation, test)
    }
}
