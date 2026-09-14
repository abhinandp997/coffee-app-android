package com.example.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coffeeapp.navigation.NavGraph
import com.example.coffeeapp.ui.theme.CoffeeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        installSplashScreen()
        setContent {
            CoffeeAppTheme {
                NavGraph()
            }
        }
    }
}
