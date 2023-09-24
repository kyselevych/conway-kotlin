class Controller(conway: Conway) : ISubscriber<Command<Int>> {

    private val conway : Conway

    init {
        this.conway = conway
    }
    override fun update(data: Command<Int>) {
        when (data.type) {
            CommandType.Set -> executeSet(data.parameters)
            CommandType.Step -> executeStep(data.parameters)
            CommandType.Back -> executeBack(data.parameters)
            CommandType.Clear -> executeClear()
            CommandType.Reset -> executeReset()
        }
    }

    private fun executeSet(parameter: Int?) {
        if (parameter == null) {
            println("Command set: parameter is undefined")
            return
        }

        val state = conway.addOrganisms(parameter)
        renderState(state)
    }

    private fun executeStep(parameter: Int?) {
        for (step in 1..(parameter ?: 1)) {
            val state = conway.step()
            renderState(state)
        }
    }

    private fun executeBack(parameter: Int?) {
        for (step in 1..(parameter ?: 1)) {
            val state = conway.back()

            if (state == null) {
                println("There are no previous steps")
                return
            }

            renderState(state)
        }
    }

    private fun executeClear() {
        val state = conway.clear()
        renderState(state)
    }

    private fun executeReset() {
        conway.reset()
        println("Grid and previous steps were reset")
    }

    private fun renderState(state: State) {
        println("Step: ${state.stepCount}")

        for (row in 0..<state.grid.rows) {
            for (col in 0..<state.grid.columns)
                print(if (state.grid[row, col] == 1) "■  " else "□  ")

            println()
        }
        println()
    }
}