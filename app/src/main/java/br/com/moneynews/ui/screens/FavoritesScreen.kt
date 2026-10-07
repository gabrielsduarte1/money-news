package br.com.moneynews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.moneynews.R
import br.com.moneynews.model.Quote
import br.com.moneynews.ui.components.BaseScreen
import br.com.moneynews.ui.components.QuoteItem
import br.com.moneynews.viewmodel.QuoteUiState

@Composable
fun FavoritesScreen(
    uiState: QuoteUiState,
    onFavoriteClick: (Quote) -> Unit,
    modifier: Modifier = Modifier
) {
    val favorites = uiState.quotes.filter { it.isFavorite }

    BaseScreen(
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        modifier = modifier
    ) {
        if (favorites.isEmpty()) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.favorites_empty_message))
            }
        } else {
            LazyColumn(modifier = modifier) {
                items(favorites) { quote ->
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