class Conway(settings: Settings) {
    private var state : State? = null
    private val settings : Settings
    private val generationProcessor : GenerationProcessor
    private val stateStorage : StateStorage

    init {
        this.settings = settings
        this.generationProcessor = GenerationProcessor()
        this.stateStorage = StateStorage()
    }

    fun addOrganisms(count: Int): State {
        val nextGrid = if (stateStorage.isEmpty())
            generationProcessor.addOrganisms(Grid(settings.rows, settings.columns), count)
        else
            generationProcessor.addOrganisms(state!!.grid, count)

        return enrichmentState(nextGrid)
    }

    fun step(): State {
        val nextGrid = if (stateStorage.isEmpty())
            Grid(settings.rows, settings.columns)
        else
            generationProcessor.nextGeneration(state!!.grid)

        return enrichmentState(nextGrid)
    }

    fun back(): State? {
        return rollbackState()
    }

    fun clear(): State {
        return enrichmentState(Grid(settings.rows, settings.columns))
    }

    fun reset() {
        state = null
        stateStorage.clear()
    }

    private fun enrichmentState(grid: Grid): State {
        if (stateStorage.isEmpty()) {
            stateStorage.push(State(grid, 1))
        } else {
            val currentState = stateStorage.peek()!!
            stateStorage.push(State(grid, currentState.stepCount + 1))
        }

        this.state = stateStorage.peek()
        return this.state!!
    }

    private fun rollbackState(): State? {
        stateStorage.pop()
        this.state = stateStorage.peek()
        return this.state
    }
}