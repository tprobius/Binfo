package com.tprobius.binformation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tprobius.binformation.presentation.screens.homescreen.HomeScreen
import com.tprobius.binformation.presentation.screens.requesthistoryscreen.RequestHistoryScreen

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object HistoryScreen : Screens(route = "history_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screens.HistoryScreen.route)
        {
            RequestHistoryScreen(navController = navController)
        }
    }
}