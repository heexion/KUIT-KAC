package com.konkuk.kuit_kac.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.presentation.diet.DietScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.kuit_kac.presentation.home.screen.HomeResultScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleInputScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScreen


@Composable
fun KacNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeScale.route,
    ) {

        composable(Route.Home.route) {
            HomeScreen()
//                            HomeObservationScreen()
        }

        composable(Route.HomeScale.route) {
            HomeScaleScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.HomeScaleInput.route) {
            HomeScaleInputScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.HomeResult.route) {
            HomeResultScreen(
                modifier = modifier,
                value = 2,
                isSucceeded = true,
                navController = navController
            )
        }


        composable(Route.Diet.route) {
            DietScreen(
                modifier = modifier

            )
        }
        composable(Route.Fitness.route) {
            FitnessScreen(
                modifier = Modifier

            )
        }
    }
}
