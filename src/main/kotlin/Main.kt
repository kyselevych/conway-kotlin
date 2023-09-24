fun main() {
    val settings = Settings(10, 10)
    val conway = Conway(settings)
    val controller = Controller(conway)

    val commandObservable = CommandObservable()
    commandObservable.register(controller)
    commandObservable.observe()
}



