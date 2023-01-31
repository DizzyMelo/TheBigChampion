package com.dicedev.thebigchampion.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun FABIcon(icon: ImageVector = Icons.Default.Add, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick.invoke() },
        shape = CircleShape,
        backgroundColor = AppColors.solidGreen,
        contentColor = Color.White
    ) {
        Icon(imageVector = icon, contentDescription = "float action button icon")
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

@Composable
fun ScreenFrame(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        content()
    }
}

@Composable
fun HorizontalDisplayCard(components: List<@Composable () -> Unit> = emptyList()) {
    Surface(
        color = AppColors.lightGray.copy(alpha = .2f),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            components.forEach { component ->
                component()
            }
        }
    }
}

@Composable
fun TitleValueBlock(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title)
        Text(text = value)
    }
}

@Composable
fun LoadingProgressIndicator(size: Dp = 28.dp, strokeWidth: Dp = 3.dp) {
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        strokeWidth = strokeWidth,
        color = AppColors.solidGreen
    )
}
