package com.bol.dangerous.operators

import java.math.BigDecimal

val stockTransactions = BigDecimal(4)
val priceTransactions = BigDecimal(3)

val stockValueTransactions = stockTransactions * priceTransactions

@Suppress("unused")
val text = stockValueTransactions.toString()
