package design_patterns

import org.junit.jupiter.api.Assertions.*

internal class FacadeTest {

    @org.junit.jupiter.api.Test
    fun test() {
        val facade = Repository(LocalDataSource(), NetworkDataSource())
        val data = facade.fetch()
        assertEquals(listOf("Harry Potter", "Ronald Weasley", "Hermione Granger"), data)
    }
}