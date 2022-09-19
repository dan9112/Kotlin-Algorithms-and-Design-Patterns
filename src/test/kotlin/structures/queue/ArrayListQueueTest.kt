package structures

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import structures.queue.Queue
import structures.queue.implementations.ArrayListQueue

internal class ArrayListQueueTest {

    private val queue: Queue<Int> = ArrayListQueue()

    @BeforeEach
    fun clear() = queue.clear()

    @org.junit.jupiter.api.Test
    fun test_is_empty() {
        assertEquals(true, queue.isEmpty())

        queue.offer(1)
        queue.offer(2)

        assertEquals(false, queue.isEmpty())

    }

    @org.junit.jupiter.api.Test
    fun test_poll() {
        queue.offer(1)
        assertEquals(1, queue.poll())
        assertEquals(null, queue.poll())
    }

    @org.junit.jupiter.api.Test
    fun test_peek() {
        queue.offer(5)
        assertEquals(5, queue.peek())
        queue.clear()
        assertEquals(null, queue.peek())
    }

    @org.junit.jupiter.api.Test
    fun test_remove() {
        queue.offer(100)
        assertEquals(100, queue.remove())

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            queue.remove()
        }
        assertEquals("queue is empty!", exception.message)
    }

    @org.junit.jupiter.api.Test
    fun test_element() {
        queue.offer(100)
        assertEquals(100, queue.element())

        queue.clear()

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            queue.element()
        }
        assertEquals("queue is empty!", exception.message)
    }

    @org.junit.jupiter.api.Test
    fun test_remove_object() {
        queue.offer(10)
        queue.offer(20)
        queue.offer(1000)

        assertEquals(true, queue.remove(20))
        assertEquals(false, queue.remove(34))
    }
}
