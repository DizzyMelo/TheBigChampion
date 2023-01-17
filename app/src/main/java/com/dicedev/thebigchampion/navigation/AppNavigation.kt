package com.dicedev.thebigchampion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicedev.thebigchampion.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.name,
        builder = {
            composable(route = AppScreens.SplashScreen.name) {
                SplashScreen(navController)
            }
        })
}