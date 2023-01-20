package com.dicedev.thebigchampion.screens.signup

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.AppLogo
import com.dicedev.thebigchampion.components.EmailTextField
import com.dicedev.thebigchampion.components.MainButton
import com.dicedev.thebigchampion.components.PasswordTextField
import com.dicedev.thebigchampion.navigation.AppScreens

@Composable
fun SignupScreen(navController: NavController, signupViewModel: SignupViewModel = hiltViewModel()) {
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
                label = "Sign up",
                isLoading = false,
                enabled = valid
            ) {
                signupViewModel.signupWithEmailAndPassword(
                    emailState.value,
                    passwordState.value,
                    onSuccessCallback = {
                        navController.navigate(AppScreens.HomeScreen.name) {
                            popUpTo(AppScreens.HomeScreen.name) { inclusive = true }
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Login", modifier = Modifier.clickable {
                navController.navigate(AppScreens.LoginScreen.name)
            })
        }
    }
}