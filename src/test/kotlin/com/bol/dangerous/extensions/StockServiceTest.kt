package com.bol.dangerous.extensions

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class StockServiceTest {
    private val stockService = StockService()

    @Test
    fun `given new stock service, expect exception when reserving stock`() {
        assertThatThrownBy { stockService.reserveStock(1) }
            .isInstanceOf(NotEnoughStockException::class.java)
    }

    @Test
    fun `given stock from untrusted source, expect exception when receiving stock`() {
        val stockReceived = StockReceived(2, "http://www.huawei.com")

        assertThatThrownBy { stockService.receiveStock(stockReceived) }
            .isInstanceOf(UntrustedSourceException::class.java)
            .hasMessage("Untrusted source: http://www.huawei.com")
    }

    @Test
    fun `given new stock received, expect stock amount present`() {
        val stockReceived = StockReceived(5, SAMSUNG_URL)

        val stock = stockService.receiveStock(stockReceived)

        assertThat(stock).isEqualTo(Stock(5))
    }
}