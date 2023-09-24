interface ISubscriber<T> {
    fun update(data: T)
}