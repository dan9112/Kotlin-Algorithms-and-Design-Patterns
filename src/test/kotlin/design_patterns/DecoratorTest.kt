package design_patterns

import org.junit.jupiter.api.Assertions

class DecoratorTest {
    @org.junit.jupiter.api.Test
    fun test() {
        val printer = ExclamationPrinter(
            WorldPrinter(
                SpacePrinter(
                    CommaPrinter(
                        HelloPrinter()
                    )
                )
            )
        )
        Assertions.assertEquals("Hello, World!", printer.printedText())
    }
}