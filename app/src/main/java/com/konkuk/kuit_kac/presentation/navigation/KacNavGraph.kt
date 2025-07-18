package com.konkuk.kuit_kac.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealMainScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealRecordScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietAddScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietPatchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietCreateScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietExistScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietMainScreen

import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.TimeInputResultScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessCreateScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessData
import com.konkuk.kuit_kac.presentation.fitness.FitnessEditResultScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessEditScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessMainScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessSearchScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeAnalysisScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeMainScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeNutritionScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeObservationScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeResultScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleInputScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealCardData
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealFastingResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealPatchScreen


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
        composable(Route.DietAdd.route) {
            DietAddScreen(
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
        composable(Route.MealExist.route) {
            MealMainScreen(
                navController = navController,
                selectedTab = "기록",
                onTabClick = {},
                onRecordClick = {},
                onFastedClick = {},
                mealCards = listOf(
                    MealCardData(
                        mealType = "아침",
                        totalKcal = "420kcal",
                        foodList = listOf(
                            Triple(painterResource(id = R.drawable.ic_sweetpotato), "고구마", "100g"),
                            Triple(painterResource(id = R.drawable.ic_salad), "샐러드", "50g"),
                            Triple(painterResource(id = R.drawable.ic_chickenbreast), "닭가슴살", "80g")
                        )
                    )
                )
            )
        }
        composable(Route.MealPatch.route){
            MealPatchScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(Route.MealFastingResult.route) {
            MealFastingResultScreen(navController = navController)
        }
        composable(Route.MealRecord.route) {
            MealRecordScreen(navController = navController)
        }
        composable(Route.MealSearch.route) {
            MealSearchScreen(navController = navController)
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

        composable("fitness_create") {
            FitnessCreateScreen(navController = navController)
        }
        composable("fitness_edit") {
            // 예: 샘플 데이터 전달
            FitnessEditScreen(
                navController = navController,
                fitnessList = remember {
                    mutableStateListOf(
                        FitnessData(1, "레그 컬", R.drawable.ic_lowerbody, onDeleteClick = { }),
                        FitnessData(2, "레그 프레스", R.drawable.ic_lowerbody, onDeleteClick = { }),
                        FitnessData(3, "레그 익스텐션", R.drawable.ic_lowerbody, onDeleteClick = { })
                    )
                }
            )
        }
        composable("fitness_edit_result") {
            FitnessEditResultScreen(navController = navController)
        }
        composable("fitness_search") {
            FitnessSearchScreen(navController = navController)
        }
        composable(route = "fitness_main") {
            FitnessMainScreen(
                navController = navController,
                fitnessData = listOf(),
                onFastedClick = { /* 구현 */ },
                onRecordClick = { /* 구현 */ },
                selectedTab = "0", // Int면 타입 맞춰서 수정
                onTabClick = { /* 탭 클릭 시 동작 */ }
            )
        }
    }
}

