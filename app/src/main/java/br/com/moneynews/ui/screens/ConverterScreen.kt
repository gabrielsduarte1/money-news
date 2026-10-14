package br.com.moneynews.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.moneynews.R
import br.com.moneynews.model.Currency
import br.com.moneynews.ui.theme.MoneyNewsTheme
import br.com.moneynews.viewmodel.ConverterUiState
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import br.com.moneynews.ui.components.CurrencySelector

@Composable
fun ConverterScreen(
    uiState: ConverterUiState,
    onAmountChange: (String) -> Unit,
    onFromChange: (Currency) -> Unit,
    onToChange: (Currency) -> Unit,
    onSwap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = uiState.amountText,
            onValueChange = onAmountChange,
            label = { Text(stringResource(R.string.converter_amount_label)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencySelector(selected = uiState.from, onCurrencySelected = onFromChange)
            IconButton(onClick = onSwap) {
                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = stringResource(R.string.converter_swap_description)
                )
            }
            CurrencySelector(selected = uiState.to, onCurrencySelected = onToChange)
        }
        Text(
            text = uiState.result?.let { formatResult(it) }
                ?: stringResource(R.string.converter_result_empty),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

private fun formatResult(value: Double): String {
    val format = NumberFormat.getNumberInstance(Locale("pt", "BR"))
    format.minimumFractionDigits = 2
    format.maximumFractionDigits = 2
    return format.format(value)
}

@Preview(showBackground = true)
@Composable
fun ConverterScreenPreview() {
    MoneyNewsTheme {
        ConverterScreen(
            uiState = ConverterUiState(
                amountText = "2",
                rates = mapOf(Currency.BRL to 1.0, Currency.USD to 5.0)
            ),
            onAmountChange = {},
            onFromChange = {},
            onToChange = {},
            onSwap = {}
        )
    }
}