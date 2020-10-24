package com.bol.dangerous.extensions

import java.math.BigDecimal

val value = BigDecimal("3.5")

val moreValue = value * 4

operator fun BigDecimal.times(int: Int) = this * BigDecimal(int)

inline class Money(private val value: BigDecimal) {
    constructor(value: Number) : this(BigDecimal(value.toString()))

    fun toCurrency(type: CurrencyType) = Currency(this, type)

    operator fun times(number: Number) = this * Money(number)
    operator fun times(money: Money) = this * money.value
    operator fun times(bigDecimal: BigDecimal) = Money(value * bigDecimal)
}

enum class CurrencyType { EURO }
data class Currency(val money: Money, val currencyType: CurrencyType)

@Suppress("unused") // Line to resolve unused
val m = Money(moreValue.toDouble()).toCurrency(CurrencyType.EURO)