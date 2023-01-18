package com.dicedev.thebigchampion.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicedev.thebigchampion.R
import com.dicedev.thebigchampion.navigation.AppScreens
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(navController: NavController = NavController(LocalContext.current)) {
    val scale = remember {
        Animatable(initialValue = .6f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = .9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )
        )

        delay(2000L)
        navController.navigate(AppScreens.LoginScreen.name)
    })


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .height(130.dp)
                    .clip(shape = CircleShape)
                    .scale(scale.value),
                contentScale = ContentScale.Fit
            )
        }
    }
}