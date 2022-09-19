package design_patterns

import org.junit.jupiter.api.Assertions.*

internal class SingletonTest {

    @org.junit.jupiter.api.Test
    fun test() {
        LocalData.addName("Twillight Sparkle")
        LocalData.addName("Starlight Glimmer")

        assertEquals(listOf("Twillight Sparkle", "Starlight Glimmer"), LocalData.names())
    }

}