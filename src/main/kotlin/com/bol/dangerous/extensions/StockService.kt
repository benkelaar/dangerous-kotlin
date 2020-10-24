package com.bol.dangerous.extensions

import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.net.URL
import java.util.concurrent.atomic.AtomicInteger

@Service
class StockService {
    private val stockAmount = AtomicInteger(0)

    fun receiveStock(stockReceived: StockReceived): Stock {
        val sourceUrl = stockReceived.sourceUrl.asUrl()
        return if (sourceUrl in TRUSTED_SOURCES)
            Stock(stockAmount + stockReceived)
        else throw UntrustedSourceException(sourceUrl)
    }

    fun reserveStock(amount: Int) =
        if (hasEnoughStockFor(amount)) Stock(stockAmount - amount)
        else throw NotEnoughStockException()

    private fun hasEnoughStockFor(amount: Int) = stockAmount.get() > amount

    private operator fun AtomicInteger.plus(received: StockReceived) = addAndGet(received.amount)
    private operator fun AtomicInteger.minus(amount: Int) = addAndGet(-amount)

    private fun String?.asUrl() = this?.let { URL(it) }
}

data class StockReceived(
    val amount: Int,
    val sourceUrl: String? = null
)

data class Stock(val amount: Int)

const val SAMSUNG_URL = "http://www.samsung.com"

private val TRUSTED_SOURCES = listOf(URL(SAMSUNG_URL))

class NotEnoughStockException : RuntimeException("Not enough stock")
class UntrustedSourceException(source: URL?) : RuntimeException("Untrusted source: $source")