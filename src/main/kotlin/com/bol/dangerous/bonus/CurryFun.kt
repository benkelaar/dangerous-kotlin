package com.bol.dangerous.bonus

fun <T, U, V> receivingStuff(instance: T, functionWithReceiver: T.(U) -> V) = { input: U -> instance.functionWithReceiver(input) }

// fun <T, U, V> receivingStuff(instance: T, functionWithReceiver: T.(U) -> V) = instance.functionWithReceiver
// or
// fun <T, U, V> receivingStuff(instance: T, functionWithReceiver: T.(U) -> V) = instance::functionWithReceiver

fun <T, U, V> curry(function: (T, U) -> V) = { t: T -> { u: U -> function(t, u) } }

fun <T, U, V, W> curry(function: (T, U, V) -> W) = { t: T -> { u: U -> { v: V -> function(t, u, v) } } }

fun <T, U, V> uncurry(function: (T) -> (U) -> V) = { t: T, u: U -> function(t)(u) }

fun <T, U, V, W> uncurry(function: (T) -> (U) -> (V) -> W) = { t: T, u: U, v: V -> function(t)(u)(v) }


fun <T, U, V> curriedReceivingStuff(instance: T, functionWithReceiver: T.(U) -> V) = curry(functionWithReceiver)(instance)
