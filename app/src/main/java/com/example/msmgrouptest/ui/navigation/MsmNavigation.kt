package com.example.msmgrouptest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.msmgrouptest.ui.MainActivityViewModel
import com.example.msmgrouptest.ui.main.MainScreen
import com.example.msmgrouptest.ui.sing_in.SingInScreen

@Composable
fun MsmNavigation(startScreen: Navigation, disconnect: MutableState<Boolean>) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startScreen.route
    ) {
        composable(route = Navigation.InitializationScreen.route) {
            disconnect.value = false
            SingInScreen(
                navigationToMainScreen = {navController.navigate(Navigation.MainScreen.route){
                    popUpTo(0)
                } }
            )
        }

        composable(route = Navigation.MainScreen.route) {
            MainScreen(
                navigateToSingIn = {navController.navigate(Navigation.InitializationScreen.route){
                    popUpTo(0)
                } }
            )
        }
    }

    if (disconnect.value){
        navController.navigate(Navigation.InitializationScreen.route){
            popUpTo(0)
        }
    }
}

sealed class Navigation(val route: String){
    object InitializationScreen:Navigation(route = "initializationScreen")

    object MainScreen:Navigation(route = "mainScreen")
}