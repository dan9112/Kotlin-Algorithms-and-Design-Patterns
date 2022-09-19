package structures.stack

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import structures.stack.Stack.Companion.emptyStackMessage

import structures.stack.implementations.ArrayListStack

class ArrayListStackTest {
    private val arrayListStack = ArrayListStack<Int>()

    @Test
    fun test_push() {
        arrayListStack.push(10)
        assertThat(arrayListStack.peek).isEqualTo(10)
    }

    @Test
    fun test_pop() {
        arrayListStack.push(20)
        assertThat(arrayListStack.pop()).isEqualTo(20)

        val exception = assertThrows(IllegalArgumentException::class.java) { arrayListStack.pop() }
        assertThat(exception.message).isEqualTo(emptyStackMessage)
    }

    @Test
    fun test_is_empty() {
        arrayListStack.clear
        assertThat(arrayListStack.isEmpty).isTrue()

        val exception = assertThrows(IllegalArgumentException::class.java) { arrayListStack.peek }
        assertThat(exception.message).isEqualTo(emptyStackMessage)
    }

    @Test
    fun test_is_not_empty() {
        arrayListStack.push(20)
        assertThat(arrayListStack.isEmpty).isFalse()
    }
}
