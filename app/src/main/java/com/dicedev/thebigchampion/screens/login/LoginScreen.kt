package com.dicedev.thebigchampion.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.AppLogo
import com.dicedev.thebigchampion.components.EmailTextField
import com.dicedev.thebigchampion.components.MainButton
import com.dicedev.thebigchampion.components.PasswordTextField

@Composable
fun LoginScreen(navController: NavController) {
    val emailState = remember {
        mutableStateOf("")
    }

    val passwordState = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLogo()
            Spacer(modifier = Modifier.height(20.dp))
            EmailTextField(emailState = emailState)
            PasswordTextField(passwordState = passwordState)
            MainButton(label = "Login")

        }
    }
}