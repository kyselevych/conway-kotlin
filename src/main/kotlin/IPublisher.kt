interface IPublisher<T>{
    fun register(subscriber: ISubscriber<T>)
    fun remove(subscriber: ISubscriber<T>)
    fun notifySubscribers(data: T)
}