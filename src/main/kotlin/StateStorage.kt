class StateStorage {
    private val items = mutableListOf<State>()

    fun push(item: State) {
        items.add(item)
    }

    fun pop(): State? {
        if (isEmpty()) return null
        return items.removeAt(items.size - 1)
    }

    fun peek(): State? {
        if (isEmpty()) return null
        return items.last()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun clear() {
        items.clear()
    }
}