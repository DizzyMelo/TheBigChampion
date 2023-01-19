package com.dicedev.thebigchampion.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.AppLogo
import com.dicedev.thebigchampion.components.EmailTextField
import com.dicedev.thebigchampion.components.MainButton
import com.dicedev.thebigchampion.components.PasswordTextField
import com.dicedev.thebigchampion.navigation.AppScreens

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = LoginViewModel()) {
    val emailState = rememberSaveable {
        mutableStateOf("")
    }

    val passwordState = rememberSaveable {
        mutableStateOf("")
    }

    val valid = remember(emailState.value, passwordState.value) {
        emailState.value.trim().isNotEmpty() && passwordState.value.trim().isNotEmpty()
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
            MainButton(
                label = "Login",
                isLoading = loginViewModel.signInLoading.value,
                enabled = valid
            ) {
                loginViewModel.signInWithEmailAndPassword(
                    emailState.value,
                    passwordState.value,
                    callback = {
                        navController.navigate(AppScreens.HomeScreen.name) {
                            popUpTo(AppScreens.HomeScreen.name) { inclusive = true }
                        }
                    }
                )
            }

            Text(text = "Create an account", modifier = Modifier.clickable {
                navController.navigate(AppScreens.SignupScreen.name)
            })
        }
    }
}