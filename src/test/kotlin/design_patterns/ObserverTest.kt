package design_patterns

import org.junit.jupiter.api.Assertions.*

internal class ObserverTest {
    @org.junit.jupiter.api.Test
    fun test() {
        val ponyList = PonyList()
        ponyList.observe { items ->
            assertEquals("Twillight Sparkle", items.first())
        }
        ponyList.add("Twillight Sparkle")
    }
}