package br.com.moneynews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.moneynews.ui.components.QuoteItem
import br.com.moneynews.viewmodel.QuoteViewModel

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val viewModel: QuoteViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (uiState.errorMessage != null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = uiState.errorMessage!!)
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(uiState.quotes) { quote ->
                QuoteItem(
                    name = quote.name,
                    code = quote.code,
                    value = quote.value,
                    change = quote.change
                )
                HorizontalDivider()
            }
        }
    }
}