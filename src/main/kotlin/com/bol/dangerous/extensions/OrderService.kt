package com.bol.dangerous.extensions

import org.springframework.stereotype.Service
import org.springframework.util.IdGenerator
import java.net.MalformedURLException
import java.net.URL
import java.util.*

@Service
class OrderService(
    private val stockService: StockService,
    private val idGenerator: IdGenerator
) {
    fun createOrder(orderRequest: OrderRequest): Order {
        stockService.reserveStock(orderRequest.amount)
        return Order(idGenerator.generateId(), orderRequest)
    }
}

data class OrderRequest(val amount: Int, val shopUrl: String)

data class Order(
    val id: UUID,
    val amount: Int,
    val shopUrl: URL
) {
    constructor(id: UUID, request: OrderRequest) : this(id, request.amount, request.shopUrl.asUrl())
}

private fun String.asUrl() = URL(this)

@Suppress("unused")
class Variations {
    private fun String?.asUrl() = this?.let { URL(it) }

    private fun String?.toUrl() =
        try { this?.let { URL(it) } }
        catch (e: MalformedURLException) { null }
}