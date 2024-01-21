package year2023.day20

class Conjunction : Module() {
    val memo = mutableMapOf<String, Boolean>()

    fun next(keys: Iterable<String>): Boolean {
        for (key in keys) {
            if (!memo.getOrDefault(key, false)) {
                return true
            }
        }

        return false
    }

    override fun reset() {
        for (entry in memo.entries) {
            entry.setValue(false)
        }
    }
}
