package br.com.moneynews.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.moneynews.ui.screens.ConverterScreen
import br.com.moneynews.ui.screens.DashboardScreen
import br.com.moneynews.ui.screens.FavoritesScreen
import br.com.moneynews.ui.screens.NewsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyNewsApp() {
    val navController = rememberNavController()
    val items = listOf(Screen.Dashboard, Screen.Favorites, Screen.Converter, Screen.News)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val title = items.find { it.route == currentRoute }?.label ?: "MoneyNews"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = { navController.navigate(screen.route) },
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = screen.label) },
                        label = { Text(text = screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Favorites.route) { FavoritesScreen() }
            composable(Screen.Converter.route) { ConverterScreen() }
            composable(Screen.News.route) { NewsScreen() }
        }
    }
}