package br.com.moneynews.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.moneynews.model.Quote
import br.com.moneynews.ui.components.BaseScreen
import br.com.moneynews.ui.components.QuoteItem
import br.com.moneynews.viewmodel.QuoteUiState

@Composable
fun DashboardScreen(
    uiState: QuoteUiState,
    onFavoriteClick: (Quote) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreen(
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        modifier = modifier
    ) {
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