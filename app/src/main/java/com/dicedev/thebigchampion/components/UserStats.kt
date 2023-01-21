package com.dicedev.thebigchampion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicedev.thebigchampion.R
import com.dicedev.thebigchampion.utils.AppColors

@Composable
fun UserStats() {
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
            MedalStat(id = R.drawable.gold_medal, count = 2, color = Color.Red.copy(alpha = .7f))
            MedalStat(
                id = R.drawable.silver_medal,
                count = 1,
                color = Color.Green.copy(alpha = .5f)
            )
            MedalStat(id = R.drawable.bronze_medal, count = 3, color = Color.Blue.copy(alpha = .5f))
        }
    }
}

@Composable
fun MedalStat(id: Int, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "Medal icon",
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(50.dp)
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = color
        )
    }
}