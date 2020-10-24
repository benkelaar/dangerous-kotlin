package com.bol.dangerous.awesome

/**
 * Small showcase of some of Kotlin's awesome features
 * - Data classes
 * - Operator overloads
 * - Type aliasing
 * - Extension functions
 * - Nullable types
 * - Type reification
 * - First class function types
 */
data class Coffee(val amount: MilliLiters) {
    fun merge(that: Coffee) = this + that

    private operator fun plus(extra: Coffee) = Coffee(amount + extra.amount)
}

typealias MilliLiters = Double
typealias CoffeeMerge = (Coffee, Coffee) -> Coffee

private val merge: CoffeeMerge = Coffee::merge

fun Double.toOunces() = this / 29.574

inline fun <reified T> T?.getClassString() = T::class.java.toString()


@Suppress("unused") // Line to resolve unused
val cType = merge(Coffee(1.0), Coffee(2.0.toOunces())).getClassString()