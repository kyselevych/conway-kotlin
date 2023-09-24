class CommandObservable: IPublisher<Command<Int>> {
    private val subscribers: ArrayList<ISubscriber<Command<Int>>> = arrayListOf()

    fun observe() {
        while(true) {
            printHelpText()
            val command = readln()
            val parsedCommand = parseCommand(command)

            if (parsedCommand == null) {
                println("Incorrect command: $command")
                continue
            }

            notifySubscribers(parsedCommand)
        }
    }

    private fun parseCommand(command: String): Command<Int>? {
        return when {
            isSetCommand(command) -> Command(CommandType.Set, parseParameters(command))
            isStepCommand(command) -> Command(CommandType.Step, parseParameters(command))
            isBackCommand(command) -> Command(CommandType.Back, parseParameters(command))
            isClearCommand(command) -> Command(CommandType.Clear)
            isResetCommand(command) -> Command(CommandType.Reset)
            else -> null
        }
    }

    private fun isSetCommand(command: String) =
        command.startsWith("set", ignoreCase = true)

    private fun isStepCommand(command: String) =
        command.startsWith("step", ignoreCase = true)

    private fun isBackCommand(command: String) =
        command.startsWith("back", ignoreCase = true)

    private fun isClearCommand(command: String) =
        command.startsWith("clear", ignoreCase = true)

    private fun isResetCommand(command: String) =
        command.startsWith("reset", ignoreCase = true)

    private fun parseParameters(command: String): Int? {
        return command.split(" ").getOrNull(1)?.toIntOrNull()
    }

    private fun printHelpText() {
        println("Commands:")
        println("set N")
        println("step N")
        println("back N")
        println("clear")
        println("reset")
    }

    override fun register(subscriber: ISubscriber<Command<Int>>) {
        subscribers.add(subscriber)
    }

    override fun remove(subscriber: ISubscriber<Command<Int>>) {
        subscribers.remove(subscriber)
    }

    override fun notifySubscribers(data: Command<Int>) {
        subscribers.forEach {
            it.update(data)
        }
    }
}