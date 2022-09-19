package other

import org.junit.jupiter.api.Assertions.*

internal class MaxTest {

    private val max = Max<Int>()

    @org.junit.jupiter.api.Test
    fun test_sorted() {
        val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(10, max.compute(items))
    }

    @org.junit.jupiter.api.Test
    fun test_reversed() {
        val items = listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        assertEquals(10, max.compute(items))
    }

    @org.junit.jupiter.api.Test
    fun test_sorted_recursive() {
        val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(10, max.computeRecursive(items))
    }

    @org.junit.jupiter.api.Test
    fun test_reversed_recursive() {
        val items = listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        assertEquals(10, max.computeRecursive(items))
    }

}