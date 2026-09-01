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
            HomeScreen()
        }

        composable(route = Screen.Details.route) {
            MechanicDetailsScreen(
                mechanic = DummyMechanicsData.mechanics[0],
                onRequestServiceClick = {

                }
            )
        }

        composable(route = Screen.RequestService.route){
            RequestServiceScreen()
        }
    }
    
}