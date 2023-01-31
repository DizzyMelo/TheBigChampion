package com.dicedev.thebigchampion.screens.group.add_player

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddPlayersToGroupScreen(
    navController: NavController,
    groupId: String? = null,
    viewModel: AddPlayersToGroupViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Add Players",
            navController = navController,
            showLogOut = false
        )
    }, scaffoldState = scaffoldState) {
        ScreenContent(viewModel, groupId) {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Player not found",
                    actionLabel = "Error",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}

@Composable
fun ScreenContent(
    viewModel: AddPlayersToGroupViewModel,
    groupId: String?,
    onFailureCallback: () -> Unit
) {
    val emailState = remember {
        mutableStateOf("")
    }
    ScreenFrame {
        Column {
            EmailTextField(emailState = emailState)
            MainButton(label = "Add PLayer") {
                viewModel.addPlayerToGroup(
                    groupId = groupId.toString(),
                    email = emailState.value,
                    onFailureCallback = onFailureCallback
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