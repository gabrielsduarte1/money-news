package br.com.moneynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.moneynews.model.Quote
import br.com.moneynews.ui.components.QuoteItem
import br.com.moneynews.ui.theme.MoneyNewsTheme
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.util.Log
import br.com.moneynews.network.RetrofitClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.awesomeApiService.getQuotes("USD-BRL")
                Log.d("MoneyNews", response.toString())
            } catch (e: Exception) {
                Log.e("MoneyNews", "Erro ao buscar cotação: ${e.message}")
            }
        }

        val quotes = listOf(
            Quote(name = "Dólar comercial", code = "USD/BRL", value = "R$ 5,42", change = "+0,32%"),
            Quote(name = "Euro", code = "EUR/BRL", value = "R$ 5,89", change = "-0,15%"),
            Quote(name = "Bitcoin", code = "BTC/BRL", value = "R$ 342.180,00", change = "-1,24%")
        )

        setContent {
            MoneyNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(quotes) { quote ->
                            QuoteItem(
                                name = quote.name,
                                code = quote.code,
                                value = quote.value,
                                change = quote.change
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyNewsTheme {
        Greeting("Android")
    }
}

