package com.dicedev.thebigchampion.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.FABIcon
import com.dicedev.thebigchampion.components.MainTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Home",
            navController = navController,
            navigationIcon = null
        )
    }, floatingActionButton = {
        FABIcon {

        }
    }) {
        HomeContent()
    }
}

@Composable
fun HomeContent() {
}
