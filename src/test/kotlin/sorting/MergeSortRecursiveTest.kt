package sorting

import TestUtils
import org.junit.jupiter.api.Assertions

class MergeSortRecursiveTest {

    @org.junit.jupiter.api.Test
    fun test_reversed_array() {
        val expected = TestUtils.list(100000)

        val actual = expected.reversed().toTypedArray()
        actual.mergeSortRecursive()

        Assertions.assertEquals(expected, actual.toList())
    }

    @org.junit.jupiter.api.Test
    fun test_random_array() {
        val actual = TestUtils.randomArray(50000)

        val expected = actual.sorted()

        actual.mergeSortRecursive()

        Assertions.assertEquals(expected, actual.toList())
    }

    @org.junit.jupiter.api.Test
    fun test_shuffled_array() {
        val expected = TestUtils.sortedArray(100000)

        val actual = expected.copyOf()
        actual.shuffle()
        actual.mergeSortRecursive()

        Assertions.assertEquals(expected.toList(), actual.toList())
    }

    @org.junit.jupiter.api.Test
    fun test_sorted_array() {
        val actual = TestUtils.sortedArray(100000)

        val expected = actual.toList()

        actual.mergeSortRecursive()

        Assertions.assertEquals(expected, actual.toList())
    }

    @org.junit.jupiter.api.Test
    fun test_random_list() {
        val actual = TestUtils.mutableRandomList(500000)

        val expected = actual.sorted()

        actual.mergeSortRecursive()

        Assertions.assertEquals(expected, actual)
    }
}
