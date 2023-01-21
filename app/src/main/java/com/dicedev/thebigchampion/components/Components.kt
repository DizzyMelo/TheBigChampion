package com.dicedev.thebigchampion.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier, color: Color = AppColors.darkOrange) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = color,
        modifier = modifier.padding(vertical = 10.dp)
    )
}