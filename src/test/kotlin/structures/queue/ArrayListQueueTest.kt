package structures.queue

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import structures.queue.Queue.Companion.emptyQueueMessage
import structures.queue.implementations.ArrayListQueue

internal class ArrayListQueueTest {

    private val queue: Queue<Int> = ArrayListQueue()

    @BeforeEach
    fun clear() = queue.clear()

    @Test
    fun test_is_empty() = with(receiver = queue) {
        assertThat(queue.isEmpty()).isTrue()

        offer(1)
        offer(2)

        assertThat(isEmpty()).isFalse()
    }

    @Test
    fun test_poll() = with(receiver = queue) {
        offer(1)
        assertThat(poll()).isEqualTo(1)
        assertThat(poll()).isNull()
    }

    @Test
    fun test_peek() = with(receiver = queue) {
        offer(5)
        assertThat(peek()).isEqualTo(5)
        clear()
        assertThat(peek()).isNull()
    }

    @Test
    fun test_remove() = with(receiver = queue) {
        offer(100)
        assertThat(remove()).isEqualTo(100)

        with(receiver = assertThrows(IllegalStateException::class.java) {
            remove()
        }) {
            assertThat(message).isEqualTo(emptyQueueMessage)
        }
    }

    @Test
    fun test_element() = with(receiver = queue) {
        offer(100)
        Assertions.assertEquals(100, element())

        clear()

        with(receiver = assertThrows(IllegalStateException::class.java) {
            element()
        }) {
            assertThat(message).isEqualTo(emptyQueueMessage)
        }
    }

    @Test
    fun test_remove_object() = with(receiver = queue) {
        offer(10)
        offer(20)
        offer(1000)

        assertThat(remove(20)).isTrue()
        assertThat(remove(34)).isFalse()
    }
}
