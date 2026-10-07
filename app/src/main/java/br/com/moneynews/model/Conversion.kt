package br.com.moneynews.model

fun convertAmount(amount: Double, fromRate: Double, toRate: Double): Double {
    if (toRate == 0.0) return 0.0
    return amount * fromRate / toRate
}