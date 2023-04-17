package com.example.countdown2.display

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val TAG = "Countdown2"

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Log.d(TAG, "In Navigation.")
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route)  {
            HomeScreen(navController)
        }
        composable(
            route = Screen.EditScreen.route + "?eventId={eventId}",
            arguments = listOf(
                navArgument("eventId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            EditScreen(navController,it.arguments?.getString("eventId"))
        }
    }
}