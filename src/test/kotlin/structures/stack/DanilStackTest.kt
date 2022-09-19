package structures.stack

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import structures.stack.implementations.DanilStack

class DanilStackTest {
    @Test
    fun test_push() {
        val arrayListStack = DanilStack<Int>()
        arrayListStack.push(10)

        assertThat(arrayListStack.peek).isEqualTo(10)
    }

    @Test
    fun test_pop() {
        val arrayListStack = DanilStack<Int>()
        arrayListStack.push(20)

        assertThat(arrayListStack.pop()).isEqualTo(20)
    }

    @Test
    fun test_is_empty() {
        val arrayListStack = DanilStack<Int>()
        arrayListStack.clear

        assertThat(arrayListStack.isEmpty).isTrue()
    }

    @Test
    fun test_is_not_empty() {
        val arrayListStack = DanilStack<Int>()
        arrayListStack.push(20)

        assertThat(arrayListStack.isEmpty).isFalse()
    }

    @Test
    fun test_max_size() {
        val arrayListStack = DanilStack<Int>(maxSize = 24u)

        assertThat(arrayListStack.maxSize).isEqualTo(24u)
    }

    @Test
    fun test_size() {
        val arrayListStack = DanilStack<Int>().apply {
            push(23)
            push(-3)
        }

        assertThat(arrayListStack.size.toInt()).isEqualTo(2)
    }

    @Test
    fun test_get() {
        val inputList = listOf(2, 6, 4, 7)
        val arrayListStack = DanilStack<Int>().apply { inputList.forEach { push(it) } }

        assertThat(arrayListStack.get()).isEqualTo(inputList)
    }
}
