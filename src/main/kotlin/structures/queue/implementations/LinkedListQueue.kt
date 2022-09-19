package structures.queue.implementations

import structures.queue.Queue
import java.util.*

/**
 * implementation using linked list LinkedList
 */
class LinkedListQueue<T> : Queue<T> {
    private val data = LinkedList<T>()

    override fun offer(item: T) = data.add(0, item)

    override fun isEmpty() = data.isEmpty()
    override fun clear() = data.clear()

    override fun element(): T = if (isEmpty()) thr() else data.peekFirst()
    override fun peek() = if (isEmpty()) null else data.peekFirst()

    override fun remove(): T = if (isEmpty()) thr() else data.removeFirst()
    override fun poll() = if (isEmpty()) null else data.removeFirst()

    override fun remove(item: T) = if (data.contains(item)) {
        data.remove(item)
        true
    } else {
        false
    }

    private fun thr(): Nothing = throw IllegalStateException(Queue.emptyQueueMessage)
}
