package br.com.moneynews.ui

sealed class Screen(val route: String, val label: String) {
    object Dashboard : Screen(route = "dashboard", label = "Painel")
    object Favorites : Screen(route = "favorites", label = "Favoritos")
    object Converter : Screen(route = "converter", label = "Conversor")
    object News : Screen(route = "news", label = "Notícias")
}