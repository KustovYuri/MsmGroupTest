package com.example.msmgrouptest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.msmgrouptest.ui.main.MainScreen
import com.example.msmgrouptest.ui.sing_in.SingInScreen

@Composable
fun MsmNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.MainScreen.route
    ) {
        composable(route = Navigation.InitializationScreen.route) {
            SingInScreen(
                navigationToMainScreen = {navController.navigate(Navigation.MainScreen.route)}
            )
        }

        composable(route = Navigation.MainScreen.route) {
            MainScreen()
        }

    }
}

sealed class Navigation(val route: String){
    object InitializationScreen:Navigation(route = "initializationScreen")

    object MainScreen:Navigation(route = "mainScreen")
}