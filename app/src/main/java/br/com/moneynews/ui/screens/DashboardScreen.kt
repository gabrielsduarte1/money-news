package br.com.moneynews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.moneynews.model.Quote
import br.com.moneynews.ui.components.QuoteItem
import br.com.moneynews.viewmodel.QuoteUiState

@Composable
fun DashboardScreen(
    uiState: QuoteUiState,
    onFavoriteClick: (Quote) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.errorMessage != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.errorMessage)
            }
        }
        else -> {
            LazyColumn(modifier = modifier) {
                items(uiState.quotes) { quote ->
                    QuoteItem(
                        name = quote.name,
                        code = quote.code,
                        value = quote.value,
                        change = quote.change,
                        isFavorite = quote.isFavorite,
                        onFavoriteClick = { onFavoriteClick(quote) }
                    )
                }
            }
        }
    }
}