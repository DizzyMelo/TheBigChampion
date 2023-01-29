package com.dicedev.thebigchampion.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*
import com.dicedev.thebigchampion.data.ScreenState
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val screenState = homeViewModel.screenState.value
    Scaffold(topBar = {
        MainTopAppBar(
            title = "Home",
            navController = navController,
            navigationIcon = null
        )
    }, floatingActionButton = {
        FABIcon {
            navController.navigate(route = AppScreens.CreateGroupScreen.name)
        }
    }) {
        HomeContent(screenState, navController)
    }
}

@Composable
fun HomeContent(screenState: ScreenState<List<Group>>, navController: NavController) {
    ScreenFrame {
        Column {
            UserStats()
            GroupsContent(
                groups = screenState.groups,
                navController = navController,
                screenState.loading
            )
        }
    }
}

@Composable
fun GroupsContent(
    groups: List<Group>?,
    navController: NavController,
    loading: Boolean
) {
    Surface {
        Column {
            SectionTitle(text = "Groups")
            Column {

                if (loading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(28.dp),
                            strokeWidth = 3.dp
                        )
                    }
                } else if (groups == null || groups.isEmpty()) {
                    MainButton(
                        label = "Create your first group",
                        onClick = { navController.navigate(AppScreens.CreateGroupScreen.name) })
                } else {
                    groups.forEach {
                        val route =
                            StringBuilder().append(AppScreens.GroupDetailsScreen.name).append("/")
                                .append(it.id).toString()
                        GroupRow(group = it) {
                            navController.navigate(route)
                        }
                    }
                }
            }
        }
    }
}