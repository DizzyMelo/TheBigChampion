package com.dicedev.thebigchampion.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*
import com.dicedev.thebigchampion.data.stubs.GroupStubs
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.navigation.AppScreens

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController = NavController(LocalContext.current)) {
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
        HomeContent(navController)
    }
}

@Composable
fun HomeContent(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            UserStats()
            GroupsContent(navController = navController)
        }
    }
}

@Composable
fun GroupsContent(
    groups: List<Group> = GroupStubs.getEmptyListOfGroups(),
    navController: NavController
) {
    Surface {
        Column {
            SectionTitle(text = "Groups")
            Column {
                if (groups.isEmpty()) {
                    MainButton(
                        label = "Create your first group",
                        onClick = { navController.navigate(AppScreens.CreateGroupScreen.name) })
                }
                groups.forEach { group ->
                    GroupRow(group)
                }
            }
        }
    }
}