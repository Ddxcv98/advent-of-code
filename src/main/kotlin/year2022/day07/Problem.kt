package year2022.day07

import IProblem

class Problem : IProblem {
    private val root = File(children = mutableMapOf())

    init {
        var curr = File()

        javaClass
            .getResourceAsStream("/2022/07.txt")!!
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
                    curr.children?.set(name, child)
                } else if (it[0].isDigit()) {
                    val (size, name) = it.split(' ')
                    val child = File(parent = curr, name = name, size = size.toULong())
                    curr.children?.set(name, child)
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

    override fun part1(): ULong {
        val files = findFiles(root) { it.isdir() && it.size() <= 100_000UL }
        return files.sumOf(File::size)
    }

    override fun part2(): ULong {
        val free = 70_000_000UL - root.size()
        val files = findFiles(root) { it.isdir() && free + it.size() >= 30_000_000UL }
        return files.minOf(File::size)
    }
}
