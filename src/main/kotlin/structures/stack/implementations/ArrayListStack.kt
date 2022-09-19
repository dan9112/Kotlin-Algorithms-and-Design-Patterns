package structures.stack.implementations

import structures.stack.Stack.Companion.emptyStackMessage
import structures.stack.Stack

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
        if (isEmpty) throw IllegalArgumentException(emptyStackMessage)
        return data.removeLast()
    }

    override val peek
        get() = if (isEmpty) throw IllegalArgumentException(emptyStackMessage)
        else data.last()

    override val isEmpty
        get() = data.isEmpty()

    override val clear
        get() = data.clear()
}
