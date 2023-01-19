package com.dicedev.thebigchampion.utils

import com.dicedev.thebigchampion.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth

object Utils {
    fun getNavigationRoute(firebaseAuth: FirebaseAuth): String {
        if (firebaseAuth.currentUser == null) {
            return AppScreens.LoginScreen.name
        }
        return AppScreens.HomeScreen.name
    }
}