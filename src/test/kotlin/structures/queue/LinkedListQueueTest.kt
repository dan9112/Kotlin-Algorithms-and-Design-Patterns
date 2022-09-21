package structures.queue

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import structures.queue.Queue.Companion.emptyQueueMessage
import structures.queue.implementations.LinkedListQueue

class LinkedListQueueTest {
    private val queue: Queue<Int> = LinkedListQueue()

    @BeforeEach
    fun clear() = queue.clear()

    @Test
    fun test_is_empty() = with(queue) {
        assertThat(isEmpty()).isTrue()

        offer(1)
        offer(2)

        assertThat(isEmpty()).isFalse()
    }

    @Test
    fun test_poll() = with(queue) {
        offer(1)
        assertThat(poll()).isEqualTo(1)
        assertThat(poll()).isNull()
    }

    @Test
    fun test_peek() = with(queue) {
        offer(5)
        assertThat(peek()).isEqualTo(5)
        clear()
        assertThat(peek()).isNull()
    }

    @Test
    fun test_remove() = with(queue) {
        offer(100)
        assertThat(remove()).isEqualTo(100)

        val exception = assertThrows(IllegalStateException::class.java) {
            remove()
        }
        assertThat(exception.message).isEqualTo(emptyQueueMessage)
    }

    @Test
    fun test_element() = with(queue) {
        offer(100)
        assertThat(element()).isEqualTo(100)

        clear()

        val exception = assertThrows(IllegalStateException::class.java) {
            queue.element()
        }
        assertThat(exception.message).isEqualTo(emptyQueueMessage)
    }

    @Test
    fun test_remove_object() = with(queue) {
        offer(10)
        offer(20)
        offer(1000)

        assertThat(queue.remove(20)).isTrue()
        assertThat(queue.remove(34)).isFalse()
    }
}
