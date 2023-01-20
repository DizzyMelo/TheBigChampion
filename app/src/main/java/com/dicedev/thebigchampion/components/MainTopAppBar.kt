package com.dicedev.thebigchampion.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicedev.thebigchampion.navigation.AppScreens
import com.dicedev.thebigchampion.utils.AppColors
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainTopAppBar(
    title: String,
    navController: NavController,
    showLogOut: Boolean = true,
    navigationIcon: ImageVector? = Icons.Filled.ArrowBack,
    navigationAction: () -> Unit = {},
    elevation: Dp = 0.dp
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (showLogOut) {
                IconButton(onClick = {
                    FirebaseAuth.getInstance().signOut()
                        .run { navController.navigate(AppScreens.LoginScreen.name) }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Logout icon"
                    )
                }
            }
        },
        elevation = elevation,
        backgroundColor = AppColors.solidGreen,
        contentColor = Color.White,
        navigationIcon = {
            if (navigationIcon != null && navigationAction != {}) {
                IconButton(onClick = { navigationAction.invoke() }) {
                    Icon(imageVector = navigationIcon, contentDescription = "Navigation icon")
                }
            }
        }
    )
}