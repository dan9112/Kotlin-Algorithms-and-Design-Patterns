package other

import org.junit.jupiter.api.Assertions

internal class SieveOfEratosthenesTest {

    @org.junit.jupiter.api.Test
    fun test_success() {
        val eratosthenes = SieveOfEratosthenes()
        val actual = eratosthenes.compute(10)
        val expected = listOf(2, 3, 5, 7)
        Assertions.assertEquals(expected, actual)
    }

    @org.junit.jupiter.api.Test
    fun test_fail() {
        val eratosthenes = SieveOfEratosthenes()
        val actual = eratosthenes.compute(5)
        val expected = listOf(4)
        Assertions.assertNotEquals(expected, actual)
    }

}