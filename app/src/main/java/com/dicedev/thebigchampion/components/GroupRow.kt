package com.dicedev.thebigchampion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.utils.AppColors

@Composable
fun GroupRow(group: Group, onClick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(15.dp),
        color = AppColors.lightGray.copy(alpha = .2f)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp),
                shape = CircleShape,
                color = AppColors.solidGreen
            ) {

            }
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(text = group.name)
                Text(text = "4 players")
            }
        }
    }
}