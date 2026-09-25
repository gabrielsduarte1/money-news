package br.com.moneynews.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.moneynews.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {

    object Dashboard : Screen(ROUTE_DASHBOARD, R.string.screen_dashboard, Icons.Default.BarChart)
    object Favorites : Screen(ROUTE_FAVORITES, R.string.screen_favorites, Icons.Default.Star)
    object Converter : Screen(ROUTE_CONVERTER, R.string.screen_converter, Icons.Default.SwapHoriz)
    object News : Screen(ROUTE_NEWS, R.string.screen_news, Icons.Default.Article)

    companion object {
        private const val ROUTE_DASHBOARD = "dashboard"
        private const val ROUTE_FAVORITES = "favorites"
        private const val ROUTE_CONVERTER = "converter"
        private const val ROUTE_NEWS = "news"
    }
}