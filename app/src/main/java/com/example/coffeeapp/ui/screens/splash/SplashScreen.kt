package com.example.coffeeapp.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import com.example.coffeeapp.R

@Composable
fun SplashScreen(
    onTimeOut: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1200)
        Log.d("Screen","Splash Screen")
        onTimeOut()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_pic),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}