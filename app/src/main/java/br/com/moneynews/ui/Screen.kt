package br.com.moneynews.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Painel", Icons.Default.BarChart)
    object Favorites : Screen("favorites", "Favoritos", Icons.Default.Star)
    object Converter : Screen("converter", "Conversor", Icons.Default.SwapHoriz)
    object News : Screen("news", "Notícias", Icons.Default.Article)
}