package structures

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import structures.queue.Queue
import structures.queue.implementations.LinkedListQueue

class LinkedListQueueTest {
    private val queue: Queue<Int> = LinkedListQueue()

    @BeforeEach
    fun clear() = queue.clear()

    @Test
    fun test_is_empty() {
        assertEquals(true, queue.isEmpty())

        queue.offer(1)
        queue.offer(2)

        assertEquals(false, queue.isEmpty())

    }

    @Test
    fun test_poll() {
        queue.offer(1)
        assertEquals(1, queue.poll())
        assertEquals(null, queue.poll())
    }

    @Test
    fun test_peek() {
        queue.offer(5)
        assertEquals(5, queue.peek())
        queue.clear()
        assertEquals(null, queue.peek())
    }

    @Test
    fun test_remove() {
        queue.offer(100)
        assertEquals(100, queue.remove())

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            queue.remove()
        }
        assertEquals("queue is empty!", exception.message)
    }

    @Test
    fun test_element() {
        queue.offer(100)
        assertEquals(100, queue.element())

        queue.clear()

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            queue.element()
        }
        assertEquals("queue is empty!", exception.message)
    }

    @Test
    fun test_remove_object() {
        queue.offer(10)
        queue.offer(20)
        queue.offer(1000)

        assertEquals(true, queue.remove(20))
        assertEquals(false, queue.remove(34))
    }
}
