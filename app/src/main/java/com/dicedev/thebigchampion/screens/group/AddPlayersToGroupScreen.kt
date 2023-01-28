package com.dicedev.thebigchampion.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddPlayersToGroupScreen(navController: NavController) {
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Add Players",
            navController = navController,
            showLogOut = false
        )
    }) {
        ScreenContent()
    }
}

@Composable
fun ScreenContent() {
    val emailState = remember {
        mutableStateOf("")
    }
    ScreenFrame {
        Column {
            EmailTextField(emailState = emailState)
            MainButton(label = "Add PLayer") {

            }
        }
    }
}