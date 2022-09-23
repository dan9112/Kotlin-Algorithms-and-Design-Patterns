package structures.linked_list.ordinary.singly

interface SinglyLinkedList<T> {
    fun add(item: T, index: Int)
    fun add(item: T)
    fun clear()
    fun addFirst(item: T)
    fun addLast(item: T)
    val first: T?
    fun toList(): List<T>
    fun contains(item: T): Boolean
    fun isEmpty(): Boolean
    fun remove(item: T): Boolean
    fun removeAt(index: Int)
    val size: Int
    fun removeFirst(): Boolean
    fun removeLast(): Boolean
    operator fun get(index: Int): T
    fun forEach(action: (item: T) -> Unit)
    fun forEachIndexed(action: (index: Int, item: T) -> Unit)
}
