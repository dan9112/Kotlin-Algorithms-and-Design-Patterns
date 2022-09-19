package other

import org.junit.jupiter.api.Assertions.*

class PalindromeAdvancedTest {

    @org.junit.jupiter.api.Test
    fun test_empty_string() {
        val text1 = ""
        assertEquals(PalindromeAdvanced(text1).isYes(), true)
    }

    @org.junit.jupiter.api.Test
    fun test_one_length() {
        val text1 = "a"
        assertEquals(PalindromeAdvanced(text1).isYes(), true)
    }

    @org.junit.jupiter.api.Test
    fun test_is_palindrome() {
        val text1 = "tenet"
        assertEquals(PalindromeAdvanced(text1).isYes(), true)

        val text2 = "friend"
        assertEquals(PalindromeAdvanced(text2).isYes(), false)
    }

    @org.junit.jupiter.api.Test
    fun test_is_not_palindrome() {
        val text1 = "white"
        assertEquals(PalindromeAdvanced(text1).isNot(), true)

        val text2 = "tenet"
        assertEquals(PalindromeAdvanced(text2).isNot(), false)
    }

}


