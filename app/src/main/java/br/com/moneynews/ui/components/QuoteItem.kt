package br.com.moneynews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.moneynews.ui.theme.MoneyNewsTheme

@Composable
fun QuoteItem(
    name: String,
    code: String,
    value: String,
    change: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = name)
            Text(text = code)
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = value)
            Text(text = change, color = Color(0xFF2E7D32))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    MoneyNewsTheme {
        QuoteItem(
            name = "Dólar comercial",
            code = "USD/BRL",
            value = "R$ 5,42",
            change = "+0,32%"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemEuroPreview() {
    MoneyNewsTheme {
        QuoteItem(
            name = "Euro",
            code = "EUR/BRL",
            value = "R$ 5,89",
            change = "-0,15%"
        )
    }
}