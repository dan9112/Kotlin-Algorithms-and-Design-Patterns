package structures.linked_list.singly_linked_list.ordinary

interface SingleLinkedList<T> {
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
    fun remove(index: Int)
    val size: Int
    fun removeFirst(): Boolean
    fun removeLast(): Boolean
    operator fun get(index: Int): T
    fun forEach(action: (item: T) -> Unit)
    fun forEachIndexed(action: (index: Int, item: T) -> Unit)
}
