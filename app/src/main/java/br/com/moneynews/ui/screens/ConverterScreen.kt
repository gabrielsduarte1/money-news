package br.com.moneynews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.moneynews.ui.theme.MoneyNewsTheme

@Composable
fun ConverterScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Tela de Conversor")
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterScreenPreview() {
    MoneyNewsTheme {
        ConverterScreen()
    }
}