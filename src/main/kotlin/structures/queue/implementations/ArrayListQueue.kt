package structures.queue.implementations

import structures.queue.Queue

/**
 * implementation using dynamic ArrayList
 */
class ArrayListQueue<T> : Queue<T> {
    private val data = ArrayList<T>()

    override fun offer(item: T) = data.add(0, item)

    override fun isEmpty() = data.isEmpty()
    override fun clear() = data.clear()

    override fun element() = if (isEmpty()) thr() else data.first()
    override fun peek() = if (isEmpty()) null else data.first()

    override fun remove() = if (isEmpty()) thr() else data.removeFirst()
    override fun poll() = if (isEmpty()) null else data.removeFirst()

    override fun remove(item: T) = if (data.contains(item)) {
        data.remove(item)
        true
    } else {
        false
    }

    private fun thr(): Nothing = throw IllegalStateException(Queue.emptyQueueMessage)
}
