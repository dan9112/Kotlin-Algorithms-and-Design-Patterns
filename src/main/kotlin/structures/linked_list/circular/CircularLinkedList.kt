package structures.linked_list.circular

interface CircularLinkedList<T> {
    val size: Int

    fun addNext(item: T, index: Int)

    fun add(item: T)

    fun addPrevious(item: T, index: Int = 0)

    fun removeAt(index: Int)

    fun remove(item: T): Boolean
    fun isEmpty(): Boolean
    val current: T

    fun toList(): List<T>
    fun goForward(steps: Int)
    fun goBack(steps: Int)

    fun clear()

    operator fun get(index:Int): T
}
