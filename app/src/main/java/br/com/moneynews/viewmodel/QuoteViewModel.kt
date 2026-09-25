package br.com.moneynews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.moneynews.R
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

    private var cotacoesAtuais: List<Quote> = emptyList()
    private var codigosFavoritos: Set<String> = emptySet()

    init {
        buscarCotacoes()
        observarFavoritos()
    }

    private fun buscarCotacoes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val response = RetrofitClient.awesomeApiService.getQuotes("USD-BRL,EUR-BRL")
                cotacoesAtuais = response.values.map { it.toQuote() }
                atualizarListaCombinada()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                val mensagemErro = getApplication<Application>().getString(R.string.dashboard_error_load_quotes)
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = mensagemErro)
                }
            }
        }
    }

    private fun observarFavoritos() {
        viewModelScope.launch {
            favoriteDao.getAll().collect { favoritos ->
                codigosFavoritos = favoritos.map { it.code }.toSet()
                atualizarListaCombinada()
            }
        }
    }

    private fun atualizarListaCombinada() {
        val quotesComFavorito = cotacoesAtuais.map { quote ->
            quote.copy(isFavorite = quote.code in codigosFavoritos)
        }
        _uiState.update { it.copy(quotes = quotesComFavorito) }
    }

    fun toggleFavorite(quote: Quote) {
        viewModelScope.launch {
            val favorito = FavoriteEntity(code = quote.code, name = quote.name)
            if (quote.isFavorite) {
                favoriteDao.delete(favorito)
            } else {
                favoriteDao.insert(favorito)
            }
        }
    }
}