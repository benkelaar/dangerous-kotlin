package com.bol.dangerous.extensions

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/stock")
class StockController(private val stockService: StockService) {
    @PostMapping
    fun receiveStock(@RequestBody stockReceived: StockReceived) = stockService.receiveStock(stockReceived)
}

@RestController
@RequestMapping("/v1/orders")
class OrderController(private val orderService: OrderService) {
    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest) = orderService.createOrder(orderRequest)
}