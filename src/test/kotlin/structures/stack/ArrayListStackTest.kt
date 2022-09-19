package structures.stack

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import structures.Stack.ArrayListStack

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
    }

    @Test
    fun test_is_empty() {
        arrayListStack.clear
        assertThat(arrayListStack.isEmpty).isTrue()
    }

    @Test
    fun test_is_not_empty() {
        arrayListStack.push(20)
        assertThat(arrayListStack.isEmpty).isFalse()
    }
}
