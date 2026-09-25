package br.com.moneynews.viewmodel

import br.com.moneynews.model.Quote

data class QuoteUiState(
    val quotes: List<Quote> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)