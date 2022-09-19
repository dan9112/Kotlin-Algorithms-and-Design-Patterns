package structures.stack.implementations

import structures.stack.Stack.Companion.emptyStackMessage
import structures.stack.Stack

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
        if (isEmpty) throw IllegalArgumentException(emptyStackMessage)
        return data.removeLast()
    }

    override val peek: T
        get() = if (isEmpty) throw IllegalArgumentException(emptyStackMessage)
        else data.peekLast()

    override val isEmpty
        get() = data.isEmpty()

    override val clear
        get() = data.clear()
}
