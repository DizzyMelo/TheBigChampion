package com.dicedev.thebigchampion.screens.group

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.MainTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GroupDetailsScreen(navController: NavController, groupId: String? = "no-id") {
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Group Details",
            navController = navController,
            navigationAction = { navController.popBackStack() })
    }) {

    }
}