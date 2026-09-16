package br.com.moneynews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.moneynews.model.Quote
import br.com.moneynews.model.toQuote
import br.com.moneynews.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes.asStateFlow()

    init {
        buscarCotacoes()
    }

    private fun buscarCotacoes() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.awesomeApiService.getQuotes("USD-BRL,EUR-BRL")
                _quotes.value = response.values.map { it.toQuote() }
            } catch (e: Exception) {

            }
        }
    }
}