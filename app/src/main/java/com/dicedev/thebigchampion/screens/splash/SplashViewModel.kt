package com.dicedev.thebigchampion.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dicedev.thebigchampion.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun selectNavigationRoute(firebaseAuth: FirebaseAuth, navController: NavController) =
        viewModelScope.launch {
            delay(2000L)
            if (firebaseAuth.currentUser != null) {
                navController.navigate(AppScreens.HomeScreen.name)
            } else {
                navController.navigate(AppScreens.LoginScreen.name)
            }
        }
}