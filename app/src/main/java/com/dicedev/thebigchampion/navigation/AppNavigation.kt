package com.dicedev.thebigchampion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicedev.thebigchampion.screens.group.AddPlayersToGroupScreen
import com.dicedev.thebigchampion.screens.group.CreateGroupScreen
import com.dicedev.thebigchampion.screens.group.GroupDetailsScreen
import com.dicedev.thebigchampion.screens.home.HomeScreen
import com.dicedev.thebigchampion.screens.login.LoginScreen
import com.dicedev.thebigchampion.screens.signup.SignupScreen
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

            composable(route = AppScreens.LoginScreen.name) {
                LoginScreen(navController)
            }

            composable(route = AppScreens.HomeScreen.name) {
                HomeScreen(navController)
            }

            composable(route = AppScreens.SignupScreen.name) {
                SignupScreen(navController)
            }

            composable(route = AppScreens.CreateGroupScreen.name) {
                CreateGroupScreen(navController)
            }

            composable(route = AppScreens.AddPlayersToGroupScreen.name) {
                AddPlayersToGroupScreen(navController)
            }

            composable(
                route = "${AppScreens.GroupDetailsScreen.name}/{groupId}", arguments = listOf(
                    navArgument(name = "groupId", builder = {
                        type = NavType.StringType
                    })
                )
            ) {
                val groupId = it.arguments?.getString("groupId")
                GroupDetailsScreen(navController, groupId)
            }
        })
}