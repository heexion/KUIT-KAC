package com.konkuk.kuit_kac.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAICompleteScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAIDetailScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAILoadingScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanAIRecomScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanCheckScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanDietMainScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanIPAddCompleteScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanIPAddScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanInPersonScreen
import com.konkuk.kuit_kac.presentation.diet.screen.PlanResultScreen
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessCreateScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessDetailRecordAddScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessDetailRecordScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessEditResultScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessFastInputScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessMainScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordResultScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRoutineEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRoutineSearchScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessSearchScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeAnalysisScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeMainScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeNutritionScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeObservationScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeResultScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleInputScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietAddScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietCreateScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietExistScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietMainScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietPatchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealCardData
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealFastingResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealMainScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealPatchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealRecordScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealTempScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealTimeScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.TimeInputResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel


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
        composable(Route.MealTime.route){
            MealTimeScreen(
                navController = navController
            )
        }

        navigation(
            route = Route.MealGraph.route,
            startDestination = Route.MealSearch.route
        ){
            composable(
                Route.MealSearch.route
            ){backStackEntry ->
                val parentEntry = remember(backStackEntry){
                    navController.getBackStackEntry(Route.MealGraph.route)
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealSearchScreen(navController = navController, mealViewModel= mealViewModel)
            }
            composable(
                route = "meal_search_detail/{foodName}",
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Route.MealGraph.route)
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                MealSearchItemDetailScreen(
                    foodName = foodName,
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
            composable(Route.MealTemp.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Route.MealGraph.route)
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealTempScreen(navController = navController, mealViewModel = mealViewModel)
            }
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
                navController = navController
            )
        }
        composable(route = "plan_in_person_add") {
            PlanIPAddScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(route = "plan_in_person_add_complete") {
            PlanIPAddCompleteScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.PlanCheck.route) {
            PlanCheckScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Route.FitnessCreate.route) {
            FitnessCreateScreen(navController = navController)
        }
        composable(Route.FitnessEdit.route) {
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
        composable(Route.FitnessEditResult.route) {
            FitnessEditResultScreen(navController = navController)
        }
        composable(Route.FitnessSearch.route) {
            FitnessSearchScreen(navController = navController)
        }
        composable(route = Route.Fitness.route){
            FitnessMainScreen(
                navController = navController,
                fitnessData = listOf(),
                onFastedClick = { /* 구현 */ },
                onRecordClick = { /* 구현 */ },
                selectedTab = "0", // Int면 타입 맞춰서 수정
                onTabClick = { /* 탭 클릭 시 동작 */ }
            )
        }
        composable(route = Route.FitnessExist.route){
            val sampleFitnessData = listOf(
                FitnessData(
                    id = 1,
                    name = "레그 컬",
                    imageRes = R.drawable.ic_lowerbody, // 실제 리소스 있어야 함
                    onDeleteClick = {}
                ),
                FitnessData(
                    id = 2,
                    name = "레그 프레스",
                    imageRes = R.drawable.ic_lowerbody,
                    onDeleteClick = {}
                ),
                FitnessData(
                    id = 3,
                    name = "레그 익스텐션",
                    imageRes = R.drawable.ic_lowerbody,
                    onDeleteClick = {}
                )
            )

            FitnessMainScreen(
                navController = navController,
                selectedTab = "기록",
                onTabClick = {},
                onRecordClick = {},
                onFastedClick = {},
                fitnessData = sampleFitnessData
            )
        }
        composable(route = Route.FitnessRecordEdit.route) {
            FitnessRecordEditScreen(
                navController = navController,
                fitnessList = remember {
                    mutableStateListOf(
                        // 실제 데이터 필요 시 ViewModel 연동
                        FitnessData(id = 0, imageRes = R.drawable.ic_lowerbody, name = "레그 컬", onDeleteClick = {}),
                        FitnessData(id = 1, imageRes = R.drawable.ic_lowerbody, name = "레그 프레스", onDeleteClick = {}),
                        FitnessData(id = 2, imageRes = R.drawable.ic_lowerbody, name = "레그 익스텐션", onDeleteClick = {})
                    )
                }
            )
        }
        composable(route = Route.FitnessRoutineEdit.route) {
            val sampleData = remember {
                listOf(
                    FitnessData(
                        id = 0,
                        name = "레그 컬",
                        imageRes = R.drawable.ic_lowerbody,
                        onDeleteClick = {}
                    ),
                    FitnessData(
                        id = 1,
                        name = "레그 프레스",
                        imageRes = R.drawable.ic_lowerbody,
                        onDeleteClick = {}
                    ),
                    FitnessData(
                        id = 2,
                        name = "레그 익스텐션",
                        imageRes = R.drawable.ic_lowerbody,
                        onDeleteClick = {}
                    )
                )
            }

            FitnessRoutineEditScreen(
                navController = navController,
                selectedTab = "기록",
                onTabClick = { /* 탭 전환 로직 */ },
                fitnessItems = sampleData
            )
        }

        composable("fitness/detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            FitnessDetailRecordScreen(
                name = name,
                navController = navController)
        }

        composable(Route.FitnessRecordResult.route) {
            FitnessRecordResultScreen(navController = navController)
        }

        composable(Route.FitnessRoutineSearch.route) {
            FitnessRoutineSearchScreen(navController = navController)
        }

        composable(Route.FitnessFastInput.route) {
            FitnessFastInputScreen(navController = navController)
        }
        composable(Route.FitnessDetailRecordAdd.route) {
            FitnessDetailRecordAddScreen(navController = navController)
        }








    }
}

