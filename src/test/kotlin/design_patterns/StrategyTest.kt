package design_patterns

import org.junit.jupiter.api.Assertions.*

internal class StrategyTest {

    @org.junit.jupiter.api.Test
    fun test() {

        val exchange = RubleExchangeRate()
        assertEquals(1.0, exchange.exchange(70.0))

        exchange.changeStrategy(ExchangeStrategy.Tenge())
        assertEquals(60.0, exchange.exchange(10.0))

    }

}