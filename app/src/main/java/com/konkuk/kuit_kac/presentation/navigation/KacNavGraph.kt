package com.konkuk.kuit_kac.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.konkuk.kuit_kac.presentation.diet.screen.FastingResultScreen
import com.konkuk.kuit_kac.presentation.diet.screen.MealEditResultScreen
import com.konkuk.kuit_kac.presentation.diet.screen.MealMainScreen
import com.konkuk.kuit_kac.presentation.diet.screen.MealRecordScreen
import com.konkuk.kuit_kac.presentation.diet.screen.MealSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.diet.screen.MealSearchScreen
import com.konkuk.kuit_kac.presentation.diet.screen.DietPatchScreen
import com.konkuk.kuit_kac.presentation.diet.screen.DietCreateScreen
import com.konkuk.kuit_kac.presentation.diet.screen.DietExistScreen
import com.konkuk.kuit_kac.presentation.diet.screen.DietMainScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAICompleteScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAIDetailScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAILoadingScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAIRecomScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanCheckScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanDietMainScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanInPersonScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanResultScreen

import com.konkuk.kuit_kac.presentation.diet.screen.TimeInputResultScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeAnalysisScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeMainScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeNutritionScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeObservationScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeResultScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleInputScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleScreen


@Composable
fun KacNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {

        composable(Route.Home.route) {
            HomeMainScreen(
                goal = 2300,
                current = 55,
                left = 300,
                navController = navController
            )
        }

        composable(Route.HomeObservation.route) {
            HomeObservationScreen(
                modifier = modifier,
                navController = navController
            )
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
            DietMainScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(Route.DietCreate.route) {
            DietCreateScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(Route.DietExist.route) {
            DietExistScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(Route.DietPatch.route) {
            DietPatchScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = Route.Meal.route) {
            MealMainScreen(
                //나중에 뷰모델로 바꿔야함
                navController = navController,
                selectedTab = "기록",
                onTabClick = { /* 탭 전환 로직 */ },
                onRecordClick = { navController.navigate("meal_record") },
                onFastedClick = { navController.navigate("fasting_result") },
                mealCards = emptyList() // 또는 실제 식단 데이터
            )
        }

        composable(Route.Fitness.route) {
            FitnessScreen(
                modifier = Modifier

            )
        }

        composable(Route.HomeAnalysis.route) {
            HomeAnalysisScreen(
                modifier = modifier
            )
        }

        composable(Route.HomeNutrition.route) {
            HomeNutritionScreen(
                modifier = modifier
            )
        }

        composable(route = "fasting_result") {
            FastingResultScreen(navController = navController)
        }

        composable("meal_record") {
            MealRecordScreen(navController = navController)
        }
        composable(route = "meal_search") {
            MealSearchScreen(navController = navController)
        }
        composable(
            route = "meal_search_detail/{foodName}",
            arguments = listOf(navArgument("foodName") { type = NavType.StringType })
        ) { backStackEntry ->
            val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
            MealSearchItemDetailScreen(foodName = foodName, navController = navController)
        }

        composable(route = "time_input_result") {
            TimeInputResultScreen(navController = navController)
        }
        composable("meal_edit_result") {
            MealEditResultScreen(navController = navController)
        }

        composable(Route.PlanDiet.route) {
            PlanDietMainScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.PlanAI.route) {
            PlanAIRecomScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = "plan_ai_loading") {
            PlanAILoadingScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = "plan_ai_complete") {
            PlanAICompleteScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = "plan_ai_detail") {
            PlanAIDetailScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = "plan_result") {
            PlanResultScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.PlanInPerson.route) {
            PlanInPersonScreen(
                modifier = modifier,
            )
        }

        composable(Route.PlanCheck.route) {
            PlanCheckScreen(
                modifier = modifier,
            )
        }


    }
}
