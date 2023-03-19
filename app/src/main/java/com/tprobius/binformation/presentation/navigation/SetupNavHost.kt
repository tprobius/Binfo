package com.tprobius.binformation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tprobius.binformation.presentation.screens.homescreen.HomeScreen
import com.tprobius.binformation.presentation.screens.searchscreen.SearchScreen

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object SearchScreen : Screens(route = "search_screen")
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

        composable(route = Screens.SearchScreen.route)
        {
            SearchScreen()
        }
    }
}