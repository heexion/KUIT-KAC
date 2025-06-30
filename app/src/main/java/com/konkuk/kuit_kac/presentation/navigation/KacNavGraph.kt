package com.konkuk.kuit_kac.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.presentation.diet.DietScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessScreen
import com.konkuk.kuit_kac.presentation.home.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun KacNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ){
        composable(route = Route.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = Route.Diet.route) {
            DietScreen(modifier = modifier)
        }
        composable(route = Route.Fitness.route) {
            FitnessScreen(modifier = modifier)
        }
    }
}