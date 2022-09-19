package structures

/**
 * data structure: stack
 *
 * description: the stack uses the LIFO principle (last in, first out), all operations are performed in O(1) time
 *
 */

interface Stack<T> {

    /**
     * adds an element to the top of the stack
     *
     * @param item - element
     */
    fun push(item: T)

    /**
     * removes the element at the top of the stack and returns it
     *
     */
    fun pop(): T

    /**
     * returns the element at the top of the stack without removing
     *
     */
    val peek: T

    /**
     * returns true if the stack is empty
     *
     */
    val isEmpty: Boolean

    /**
     * clears the stack
     *
     */
    val clear: Unit

    /**
     * implementation using ArrayList
     *
     * @param T - stack element type
     */
    class ArrayListStack<T> : Stack<T> {
        private val data = ArrayList<T>()

        override fun push(item: T) {
            data.add(item)
        }

        override fun pop(): T {
            if (isEmpty) throw IllegalArgumentException("Stack is empty!")
            return data.removeLast()
        }

        override val peek
            get() = if (isEmpty) throw IllegalArgumentException("Stack is empty!")
            else data.last()

        override val isEmpty
            get() = data.isEmpty()

        override val clear
            get() = data.clear()
    }

    /**
     * linked list implementation
     *
     * @param T - тип элементов стэка
     */
    class LinkedListStack<T> : Stack<T> {
        private val data = java.util.LinkedList<T>()

        override fun push(item: T) {
            data.add(item)
        }

        override fun pop(): T {
            if (isEmpty) throw IllegalArgumentException("Stack is empty!")
            return data.removeLast()
        }

        override val peek
            get() = if (isEmpty) throw IllegalArgumentException("Stack is empty!")
            else data.peekLast()

        override val isEmpty
            get() = data.isEmpty()

        override val clear
            get() = data.clear()

    }

    companion object {
        /**
         * Constant string for no such element exception message
         */
        private const val emptyStackMessage = "Stack is empty"

        /**
         * Constant string for stack overflow exception message
         */
        private const val overflowStackMessage = "Stack is full"
    }

    /**
     * The custom stack whose elements are stored in a singly linked list, so memory is allocated for
     * elements at runtime, and it is the developer's responsibility to control memory usage. You can
     * only push or pop nodes to the stack one at a time.
     *
     * Note: stack is not limited if [maxSize] = 0u.
     */
    open class DanilStack<T : Any>(val maxSize: UInt = 0u) : Stack<T> {
        /**
         * The content of the stack. Implemented as a singly linked list
         */
        private var data = Node<T>(null)

        /**
         * Number of stack elements
         */
        private var numbers = 0u

        /**
         * Stack element. Implemented as a singly linked list node
         */
        private class Node<T>(var value: T?, var linkToPrevious: Node<T>? = null) {
            /**
             * Clear node
             */
            val clear
                get() = if (value == null && linkToPrevious == null) false
                else {
                    value = null
                    linkToPrevious = null
                    true
                }

            override fun toString(): String {
                return if (linkToPrevious == null) "$value" else "${linkToPrevious.toString()} <- $value"
            }
        }

        @Throws(IllegalArgumentException::class)
        override fun push(item: T) {
            if (isFull) throw IllegalArgumentException(overflowStackMessage)
            else {
                if (isEmpty) data.value = item
                else data = Node(item, linkToPrevious = data)
                numbers++
            }
        }

        @Throws(IllegalArgumentException::class)
        override fun pop() = if (isEmpty) throw IllegalArgumentException(emptyStackMessage) else {
            val value = data.value!!
            with(receiver = data.linkToPrevious) {
                if (this != null) data = this else data.value = null
            }
            numbers--
            value
        }

        /**
         * Number of items on the stack
         */
        val size
            get() = numbers

        /**
         * Check for full
         */
        val isFull
            get() = numbers == maxSize && maxSize != 0u

        override val isEmpty
            get() = numbers == 0u

        override val peek
            get() = if (data.value == null) throw IllegalArgumentException(emptyStackMessage) else data.value!!

        override fun toString() = if (isEmpty) emptyStackMessage else data.toString()

        /**
         * Get stack content as a collection
         */
        fun get() = when {
            isEmpty -> emptyList()
            // this variant will resolve correctly when deleting the row and using the next branch,
            // but will consume more resources
            data.linkToPrevious == null -> listOf(data.value!!)
            else -> {
                var first = false
                var node = data
                val result = mutableListOf<T>()
                while (!first) {
                    result.add(node.value!!)
                    with(receiver = node.linkToPrevious) {
                        if (this == null) first = true
                        else node = this
                    }
                }
                result.reversed()
            }
        }

        override val clear: Unit
            get() {
                data.clear
            }
    }
}
