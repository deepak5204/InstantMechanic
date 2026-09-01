package com.example.instantmechanic.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object Details : Screen("details")

    data object RequestService : Screen("request_service")
}