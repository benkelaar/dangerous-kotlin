package com.bol.dangerous.extensions

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.net.URL
import java.util.*

class OrderServiceTest {
    private val stockService = StockService()
    private val service = OrderService(stockService) { TEST_ID }

    @Test
    fun `when creating new order with enough stock, expect ID generated`() {
        stockService.receiveStock(StockReceived(5, SAMSUNG_URL))
        val shopUrl = "http://bol.com/awesomeproduct"
        val orderRequest = OrderRequest(3, shopUrl)

        val order = service.createOrder(orderRequest)

        assertThat(order).isEqualTo(Order(TEST_ID, 3, URL(shopUrl)))
    }

    @Test
    fun `when creating new order without stock, exception thrown`() {
        val orderRequest = OrderRequest(2, "http://bol.com/awesomerproduct")

        assertThatThrownBy {
            service.createOrder(orderRequest)
        }.isInstanceOf(NotEnoughStockException::class.java)
    }

    companion object {
        private val TEST_ID = UUID.randomUUID();
    }
}

