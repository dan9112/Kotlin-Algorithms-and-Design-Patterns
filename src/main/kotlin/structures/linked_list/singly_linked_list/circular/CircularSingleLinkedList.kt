package structures.linked_list.singly_linked_list.circular

interface CircularSingleLinkedList<T> {
    val size: Int

    fun addNext(item: T, index: Int)

    fun addNext(item: T)

    fun addPrevious(item: T, index: Int = 0)

    fun remove(index: Int): Boolean

    fun remove(item: T): Boolean
    fun isEmpty(): Boolean
    val current: T

    fun asList(): List<T>
    fun goForward(steps: Int)
    fun goBack(steps: Int)
}
