package com.dicedev.thebigchampion.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dicedev.thebigchampion.R
import com.dicedev.thebigchampion.utils.AppColors

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int = R.drawable.app_logo,
    height: Dp = 130.dp
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "App logo",
        modifier = modifier
            .height(height)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun FABIcon(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick.invoke() },
        shape = CircleShape,
        backgroundColor = AppColors.solidGreen,
        contentColor = Color.White
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
    }
}