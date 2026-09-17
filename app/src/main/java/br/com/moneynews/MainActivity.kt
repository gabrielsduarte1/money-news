package br.com.moneynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.moneynews.ui.MoneyNewsApp
import br.com.moneynews.ui.theme.MoneyNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyNewsTheme {
                MoneyNewsApp()
            }
        }
    }
}