package br.com.moneynews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.moneynews.model.toQuote
import br.com.moneynews.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuoteUiState())
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    init {
        buscarCotacoes()
    }

    private fun buscarCotacoes() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            try {
                val response = RetrofitClient.awesomeApiService.getQuotes("USD-BRL,EUR-BRL")
                val quotes = response.values.map { it.toQuote() }
                _uiState.value = _uiState.value.copy(quotes = quotes, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Não foi possível carregar as cotações"
                )
            }
        }
    }
}