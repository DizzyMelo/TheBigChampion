package com.dicedev.thebigchampion.screens.group.add_player

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.TheBigChampionApplication
import com.dicedev.thebigchampion.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddPlayersToGroupScreen(
    navController: NavController,
    groupId: String? = null,
    viewModel: AddPlayersToGroupViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Add Players",
            navController = navController,
            showLogOut = false
        )
    }) {
        ScreenContent(viewModel, groupId)
    }
}

@Composable
fun ScreenContent(viewModel: AddPlayersToGroupViewModel, groupId: String?) {
    val emailState = remember {
        mutableStateOf("")
    }
    ScreenFrame {
        Column {
            EmailTextField(emailState = emailState)
            MainButton(label = "Add PLayer") {
                viewModel.addPlayerToGroup(
                    groupId.toString(),
                    TheBigChampionApplication.activeUserId.toString()
                )
            }
            LazyColumn(content = {
                items(items = viewModel.playersAdded) { item ->
                    Text(text = item)
                }
            })
        }
    }
}