package year2023.day20

abstract class Module {
    lateinit var name: String
    lateinit var onSignal: (Pulse) -> Unit

    abstract fun reset()
}
