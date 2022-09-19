package structures.queue

/**
 * data structure: queue
 *
 * description: the queue is organized on a FIFO basis (first in, first out), all operations are performed in O(1),
 * except for the operation of deleting from the middle of the queue, which in the worst case takes O(n) time
 *
 */

interface Queue<T> {

    /**
     * adding to the front of the queue
     *
     * @param item - added element
     */
    fun offer(item: T)

    /**
     * returns the element from the front of the queue
     *
     * if the queue is empty, throws an IllegalStateException
     */
    fun element(): T

    /**
     * returns the element from the front of the queue
     *
     * if the queue is empty it will return null
     */
    fun peek(): T?

    /**
     * removes and returns an element from the front of the queue
     *
     * if the queue is empty, throws an IllegalStateException
     */
    fun remove(): T

    /**
     * removes and returns an element from the front of the queue
     *
     * if the queue is empty it will return null
     */
    fun poll(): T?

    /**
     *
     * @return returns true if the queue is empty
     */
    fun isEmpty(): Boolean

    /**
     * clears the queue
     *
     */
    fun clear()

    /**
     * removes an element from the middle of the queue
     *
     * @return returns true if the element was successfully removed
     */
    fun remove(item: T): Boolean

    companion object {
        /**
         * Constant string for no such element exception message
         */
        const val emptyQueueMessage = "Queue is empty"
    }
}
