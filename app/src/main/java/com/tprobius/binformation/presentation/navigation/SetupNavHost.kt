package com.tprobius.binformation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tprobius.binformation.presentation.screens.historyscreen.HistoryScreen
import com.tprobius.binformation.presentation.screens.historyscreen.SearchScreen

sealed class Screens(val route: String) {
    object SearchScreen : Screens(route = "search_screen")
    object HistoryScreen : Screens(route = "history_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SearchScreen.route
    ) {
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Screens.HistoryScreen.route)
        {
            HistoryScreen()
        }
    }
}