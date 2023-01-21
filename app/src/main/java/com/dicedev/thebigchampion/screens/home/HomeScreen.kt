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
        HomeContent()
    }
}

@Composable
fun HomeContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            UserStats()
            GroupsContent()
        }
    }
}

@Composable
fun GroupsContent(groups: List<Group> = GroupStubs.getEmptyListOfGroups()) {
    Surface {
        Column {
            SectionTitle(text = "Groups")
            Column {
                if (groups.isEmpty()) {
                    Row {
                        MainButton(label = "Create your first group", onClick = {})
                    }
                }
                groups.forEach { group ->
                    GroupRow(group)
                }
            }
        }
    }
}