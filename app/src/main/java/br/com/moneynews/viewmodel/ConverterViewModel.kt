package br.com.moneynews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.moneynews.R
import br.com.moneynews.model.Currency
import br.com.moneynews.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConverterViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ConverterUiState())
    val uiState: StateFlow<ConverterUiState> = _uiState.asStateFlow()

    init {
        loadRates()
    }

    private fun loadRates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val pairs = Currency.entries
                    .filter { it != Currency.BRL }
                    .joinToString(",") { "${it.code}-BRL" }
                val response = RetrofitClient.awesomeApiService.getQuotes(pairs)

                val rates = mutableMapOf(Currency.BRL to 1.0)
                response.values.forEach { quote ->
                    val currency = Currency.entries.find { it.code == quote.code }
                    val rate = quote.bid.toDoubleOrNull()
                    if (currency != null && rate != null) {
                        rates[currency] = rate
                    }
                }
                _uiState.update { it.copy(rates = rates, isLoading = false) }
            } catch (e: Exception) {
                val message = getApplication<Application>().getString(R.string.converter_error_load_rates)
                _uiState.update { it.copy(isLoading = false, errorMessage = message) }
            }
        }
    }

    fun onAmountChange(text: String) {
        _uiState.update { it.copy(amountText = text) }
    }

    fun onFromCurrencyChange(currency: Currency) {
        _uiState.update { it.copy(from = currency) }
    }

    fun onToCurrencyChange(currency: Currency) {
        _uiState.update { it.copy(to = currency) }
    }

    fun onSwap() {
        _uiState.update { it.copy(from = it.to, to = it.from) }
    }

}
