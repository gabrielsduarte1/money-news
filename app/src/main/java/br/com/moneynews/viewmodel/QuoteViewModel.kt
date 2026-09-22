package br.com.moneynews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.moneynews.local.FavoriteEntity
import br.com.moneynews.local.MoneyNewsDatabase
import br.com.moneynews.model.Quote
import br.com.moneynews.model.toQuote
import br.com.moneynews.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao = MoneyNewsDatabase.getInstance(application).favoriteDao()

    private val _uiState = MutableStateFlow(QuoteUiState())
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    init {
        buscarCotacoes()
        observarFavoritos()
    }

    private fun buscarCotacoes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val response = RetrofitClient.awesomeApiService.getQuotes("USD-BRL,EUR-BRL")
                val quotes = response.values.map { it.toQuote() }
                _uiState.update { it.copy(quotes = quotes, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = "Não foi possível carregar as cotações")
                }
            }
        }
    }

    private fun observarFavoritos() {
        viewModelScope.launch {
            favoriteDao.getAll().collect { favoritos ->
                val codigos = favoritos.map { it.code }.toSet()
                _uiState.update { it.copy(favoriteCodes = codigos) }
            }
        }
    }

    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            val favorito = FavoriteEntity(code = quote.code, name = quote.name)
            if (quote.code in _uiState.value.favoriteCodes) {
                favoriteDao.delete(favorito)
            } else {
                favoriteDao.insert(favorito)
            }
        }
    }
}