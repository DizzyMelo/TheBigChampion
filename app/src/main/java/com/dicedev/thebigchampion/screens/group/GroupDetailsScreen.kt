package com.dicedev.thebigchampion.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GroupDetailsScreen(
    navController: NavController = NavController(LocalContext.current),
    groupId: String? = "no-id"
) {
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Group Details",
            navController = navController,
            navigationAction = { navController.popBackStack() },
            showLogOut = false
        )
    }) {
        GroupDetailsContent()
    }
}

@Composable
fun GroupDetailsContent() {
    ScreenFrame {
        Column {
            HorizontalDisplayCard(listOf(
                { TitleValueBlock(title = "Players", value = "7") },
                { TitleValueBlock(title = "Games", value = "6") }
            ))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SectionTitle(text = "Players")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Game Icon")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SectionTitle(text = "Games")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Game Icon")
                }
            }
        }
    }
}