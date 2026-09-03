package br.com.moneynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.moneynews.ui.theme.MoneyNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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

@Composable
fun QuoteItem() {
    Row {
        Column {
            Text(text = "Dólar comercial")
            Text(text = "USD/BRL")
        }
        Column {
            Text(text = "R$ 5,42")
            Text(text = "+0,32%")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyNewsTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    MoneyNewsTheme {
        QuoteItem()
    }
}