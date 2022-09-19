package structures.stack

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import structures.stack.implementations.LinkedListStack

class LinkedListStackTest {
    private val arrayListStack = LinkedListStack<Int>()

    @Test
    fun test_push() {
        arrayListStack.push(10)
        assertThat(arrayListStack.peek).isEqualTo(10)
    }

    @Test
    fun test_pop() {
        arrayListStack.push(20)
        assertThat(arrayListStack.pop()).isEqualTo(20)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) { arrayListStack.pop() }
        assertThat(exception.message).isEqualTo(Stack.emptyStackMessage)
    }

    @Test
    fun test_is_empty() {
        arrayListStack.clear
        assertThat(arrayListStack.isEmpty).isTrue()

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) { arrayListStack.peek }
        assertThat(exception.message).isEqualTo(Stack.emptyStackMessage)
    }

    @Test
    fun test_is_not_empty() {
        arrayListStack.push(20)
        assertThat(arrayListStack.isEmpty).isFalse()
    }
}
