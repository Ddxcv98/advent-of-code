package year2022

import Problem

private class File(
    val parent: File? = null,
    val name: String? = null,
    private var size: ULong? = null,
    val children: MutableMap<String, File>? = null
) {
    private fun calcSize(): ULong {
        var size = 0UL

        for (entry in iterdir()) {
            size += entry.size()
        }

        return size
    }

    fun isdir() = children != null

    fun iterdir() = if (isdir()) { children!!.values.asSequence() } else { emptySequence() }

    fun size(): ULong {
        if (size == null) {
            size = calcSize()
        }

        return size!!
    }
}

class Day7 : Problem {
    private val root = File(children = mutableMapOf())

    init {
        var curr = root

        javaClass
            .getResourceAsStream("/2022/7.txt")!!
            .bufferedReader()
            .forEachLine {
                if (it.startsWith("$ cd")) {
                    val name = it.substring(5)

                    curr = when (name) {
                        "/" -> root
                        ".." -> curr.parent!!
                        else -> curr.children!![name]!!
                    }
                } else if (it.startsWith('d')) {
                    val name = it.substring(4)
                    val child = File(parent = curr, name = name, children = mutableMapOf())
                    curr.children?.put(name, child)
                } else if (it[0].isDigit()) {
                    val (size, name) = it.split(' ')
                    val child = File(parent = curr, name = name, size = size.toULong())
                    curr.children?.put(name, child)
                }
            }
    }

    private fun findFiles(file: File, predicate: (File) -> Boolean): List<File> {
        val files = mutableListOf<File>()

        if (predicate(file)) {
            files.add(file)
        }

        for (entry in file.iterdir()) {
            files.addAll(findFiles(entry, predicate))
        }

        return files
    }

    override fun part1() {
        val files = findFiles(root) { it.isdir() && it.size() <= 100_000UL }
        println(files.sumOf(File::size))
    }

    override fun part2() {
        val free = 70_000_000UL - root.size()
        val files = findFiles(root) { it.isdir() && free + it.size() >= 30_000_000UL }
        println(files.minOf(File::size))
    }
}
