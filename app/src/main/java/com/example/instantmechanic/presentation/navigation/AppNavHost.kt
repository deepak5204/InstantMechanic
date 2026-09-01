package com.example.instantmechanic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.presentation.details.MechanicDetailsScreen
import com.example.instantmechanic.presentation.home.HomeScreen
import com.example.instantmechanic.presentation.service.RequestServiceScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route){
            HomeScreen(
                onMechanicClick = { mechanic ->
                    navController.navigate(
                        "${Screen.Details.route}/${mechanic.id}"
                    )
                }
            )
        }

        composable("${Screen.Details.route}/{mechanicId}") { backStackEntry ->

            val mechanicId = backStackEntry.arguments
                ?.getString("mechanicId")
                ?.toIntOrNull()

            val mechanic = DummyMechanicsData.mechanics
                .find { it.id == mechanicId }

            if (mechanic != null) {
                MechanicDetailsScreen(
                    mechanic = mechanic,
                    onRequestServiceClick = {
                        navController.navigate(Screen.RequestService.route)
                    }
                )
            }
        }

        composable(route = Screen.RequestService.route){
            RequestServiceScreen()
        }
    }
    
}