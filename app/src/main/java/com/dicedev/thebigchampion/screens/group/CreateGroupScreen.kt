package com.dicedev.thebigchampion.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.FABIcon
import com.dicedev.thebigchampion.components.MainButton
import com.dicedev.thebigchampion.components.MainTextField
import com.dicedev.thebigchampion.components.MainTopAppBar
import com.dicedev.thebigchampion.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateGroupScreen(
    navController: NavController,
    groupViewModel: GroupViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Create a group",
                navController = navController,
                navigationAction = { navController.popBackStack() })
        }, floatingActionButton = {
            FABIcon(icon = Icons.Default.ArrowForward) {

            }
        }) {
        val groupName = remember {
            mutableStateOf("")
        }

        val valid = remember(groupName.value) {
            groupName.value.length > 2
        }
        Column(modifier = Modifier.padding(10.dp)) {
            MainTextField(valueState = groupName, label = "Group Name")
            MainButton(label = "Next", enabled = valid) {
                groupViewModel.createGroup(groupName.value) {
                    navController.navigate(AppScreens.AddPlayersToGroupScreen.name)
                }
            }
        }
    }
}