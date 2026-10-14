package br.com.moneynews.viewmodel

import br.com.moneynews.model.Currency
import br.com.moneynews.model.convertAmount

data class ConverterUiState(
    val amountText: String = "1",
    val from: Currency = Currency.USD,
    val to: Currency = Currency.BRL,
    val rates: Map<Currency, Double> = mapOf(Currency.BRL to 1.0),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val result: Double?
        get() {
            val amount = amountText.replace(',', '.').toDoubleOrNull() ?: return null
            val fromRate = rates[from] ?: return null
            val toRate = rates[to] ?: return null
            return convertAmount(amount, fromRate, toRate)
        }
}