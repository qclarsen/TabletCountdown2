package com.example.countdown2.display

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object EditScreen : Screen("edit_screen")
}
