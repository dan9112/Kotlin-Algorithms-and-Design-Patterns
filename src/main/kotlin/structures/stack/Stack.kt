package structures.stack

/**
 * Data structure: stack
 *
 * description: the stack uses the LIFO principle (last in, first out), all operations are performed in O(1) time
 */
interface Stack<T> {

    /**
     * Adds an element to the top of the stack
     *
     * @param item - element
     */
    fun push(item: T)

    /**
     * Removes the element at the top of the stack and returns it
     *
     */
    fun pop(): T

    /**
     * Returns the element at the top of the stack without removing
     *
     */
    val peek: T

    /**
     * Returns true if the stack is empty
     */
    val isEmpty: Boolean

    /**
     * Clears the stack
     */
    val clear: Unit

    companion object {
        /**
         * Constant string for no such element exception message
         */
        const val emptyStackMessage = "Stack is empty"

        /**
         * Constant string for stack overflow exception message
         */
        const val overflowStackMessage = "Stack is full"
    }
}
