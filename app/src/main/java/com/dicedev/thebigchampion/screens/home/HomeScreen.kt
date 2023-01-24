package com.dicedev.thebigchampion.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicedev.thebigchampion.components.*
import com.dicedev.thebigchampion.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
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
        HomeContent(navController, homeViewModel)
    }
}

@Composable
fun HomeContent(navController: NavController, homeViewModel: HomeViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            UserStats()
            GroupsContent(homeViewModel = homeViewModel, navController = navController)
        }
    }
}

@Composable
fun GroupsContent(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    Surface {
        Column {
            SectionTitle(text = "Groups")
            Column {
                if (homeViewModel.groups.collectAsState().value.isEmpty()) {
                    MainButton(
                        label = "Create your first group",
                        onClick = { navController.navigate(AppScreens.CreateGroupScreen.name) })
                }
                homeViewModel.groups.collectAsState().value.forEach {
                    GroupRow(group = it)
                }
            }
        }
    }
}