package com.ase.navigation.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ase.navigation.screens.BillboardScreen
import com.ase.navigation.screens.HomeScreen
import com.ase.navigation.screens.LibraryScreen
import com.ase.navigation.screens.ProfileScreen
import com.ase.navigation.screens.library.DetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    navController.addOnDestinationChangedListener { controller, _, _ ->
        val backStackNames = controller
            .currentBackStack.value
            .map { it.destination.route }
            .joinToString(" -> ")

        Log.d("BackStackLog", "Back stack: $backStackNames")
    }

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        composable(route = AppScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.Billboard.route) {
            BillboardScreen(navController)
        }
        composable(route = AppScreens.Profile.route) {
            ProfileScreen(navController)
        }

        navigation(
            route = AppScreens.Library.route,
            startDestination = AppScreens.Library.List.route
        ) {
            composable(route = AppScreens.Library.List.route) {
                LibraryScreen(navController)
            }
            composable(
                route = AppScreens.Library.Detail.route + "/{id}",
                arguments = listOf(navArgument(name = "id") {
                    type = NavType.StringType
                })
            ) {
                DetailScreen(navController, it.arguments?.getString("id"))
            }
        }
    }
}