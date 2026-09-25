package com.example.coffeeapp.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffeeapp.ui.screens.Cart.CartScreen
import com.example.coffeeapp.ui.screens.Cart.CartViewModel
import com.example.coffeeapp.ui.screens.Explorer.ExplorerScreen
import com.example.coffeeapp.ui.screens.HomeScreen.HomeScreen
import com.example.coffeeapp.ui.screens.HomeScreen.components.BottomBar
import com.example.coffeeapp.ui.screens.ItemScreen.ItemScreen
import com.example.coffeeapp.ui.screens.MyOrder.MyOrderScreen
import com.example.coffeeapp.ui.screens.Profile.ProfileScreen
import com.example.coffeeapp.ui.screens.SearchScreen.SearchScreen
import com.example.coffeeapp.ui.screens.onBoarding.OnboardingScreen
import com.example.coffeeapp.ui.screens.onBoarding.OnboardingViewModel
import com.example.coffeeapp.ui.screens.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()

    val hasCompletedOnboarding by onboardingViewModel.hasCompletedOnboarding.collectAsStateWithLifecycle()

    if (hasCompletedOnboarding == null) {
        return
    }

    val startDestination = if (hasCompletedOnboarding == true) {
        Routes.Splash.route
    } else {
        Routes.OnBoarding.route
    }

    val bottomBarRoutes = listOf(
        Routes.HomeScreen.route,
        Routes.Cart.route,
        Routes.Explorer.route,
        Routes.MyOrder.route,
        Routes.Profile.route
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        },
        contentWindowInsets = WindowInsets(0,0,0,0)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.OnBoarding.route) {
                OnboardingScreen(
                    onGetStarted = {
                        onboardingViewModel.completeOnboarding()
                        navController.navigate(Routes.Splash.route) {
                            popUpTo(Routes.OnBoarding.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Routes.Splash.route) {
                SplashScreen(
                    onTimeOut = {
                        navController.navigate(Routes.HomeScreen.route) {
                            popUpTo(Routes.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Routes.HomeScreen.route) {
                HomeScreen(
                    onSearch = { query ->
                        navController.navigate(Routes.SearchResult.createRoute(query))
                    },
                    onCoffeeItemClick = { item ->
                        navController.navigate(Routes.SearchResult.createRoute(item.title))
                    }
                )
            }

            composable(Routes.Cart.route) {
                CartScreen(
                    onBack = { navController.popBackStack() },
                    onCheckout = {},
                    cartViewModel = cartViewModel
                )
            }

            composable(Routes.Explorer.route) {
                ExplorerScreen()
            }

            composable(Routes.MyOrder.route) {
                MyOrderScreen()
            }

            composable(Routes.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = "search_query/{query}",
                arguments = listOf(
                    navArgument("query") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchScreen(
                    query = query,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onItemClick = { item ->
                        navController.navigate(
                            Routes.ItemScreen.createRoute(
                                item.title
                            )
                        )
                    }
                )
            }

            composable(
                Routes.ItemScreen.route,
                arguments = listOf(
                    navArgument("itemTitle") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val itemTitle = backStackEntry.arguments?.getString("itemTitle") ?: ""
                ItemScreen(
                    onBack = { navController.popBackStack() },
                    itemTitle = itemTitle,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}