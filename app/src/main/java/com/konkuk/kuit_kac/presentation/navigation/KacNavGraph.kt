package com.konkuk.kuit_kac.presentation.navigation


import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessAddDetailRecordScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessAddRecordEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessCreateScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessDetailInputScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessDetailRecordAddScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessDetailRecordScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessEditResultScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessFastInputScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessMainScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordMainScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordResultScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRecordScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRoutineEditScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessRoutineSearchScreen
import com.konkuk.kuit_kac.presentation.fitness.screen.FitnessSearchScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeMainScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeNutritionScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeObservationScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeResultScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleInputScreen
import com.konkuk.kuit_kac.presentation.home.screen.HomeScaleScreen
import com.konkuk.kuit_kac.presentation.login.screen.LoginEmailScreen
import com.konkuk.kuit_kac.presentation.login.screen.LoginMainScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel.DietViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietCreateScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietEditSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietEditSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietEditTempScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietExistScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietMainScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietPatchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditResultScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditSearchItemDetailScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditTempScreen
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealEditTimeScreen
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
import com.konkuk.kuit_kac.presentation.mealdiet.plan.screen.PlanIPSearchScreen
import com.konkuk.kuit_kac.presentation.mealdiet.plan.screen.PlanIPTempScreen
import com.konkuk.kuit_kac.presentation.mealdiet.plan.screen.PlanItemScreen
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingActivityLevel
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAiIntro
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAiMeal
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAppetite
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingCheck
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingDelivery
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingDiet
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingDietSpeed
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingEoDrink
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingFailEx
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingFinal
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingFloatingButton
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingGray
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingHamCoach
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingInput
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingInputResult
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingIntroduce
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeHam
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeNyam
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeScale
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMeal
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingNyamCoach
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingPreferType
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingStart
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingWeek
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingYellow
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingActivityLevelScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingAiIntroScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingAiMealScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingAppetiteScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingCheckScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingDeliveryScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingDietScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingDietSpeedScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingEoDrinkScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingFailExScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingFinalScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingFloatingButtonScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingGrayScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingHamCoachScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingInputResultScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingInputScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingIntroduceScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingMainHomeHamScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingMainHomeNyamScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingMainHomeScaleScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingMealScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingNyamCoachScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingPreferTypeScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingStartScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingWeekScreen
import com.konkuk.kuit_kac.presentation.onboarding.screen.OnboardingYellowScreen


@Composable
fun KacNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isFirstLaunch = prefs.getBoolean("isFirstLaunch", false)

    NavHost(
        navController = navController,
        startDestination = if (isFirstLaunch) OnboardingStart.route else Route.Home.route
    ) {
        // 온보딩 스타트
        composable(route = OnboardingStart.route) {
            OnboardingStartScreen(
                navController = navController,
                onFinish = {
                    prefs.edit().putBoolean("isFirstLaunch", false).apply()
                    navController.navigate(OnboardingStart.route)
                }
            )
        }

        composable(Route.Home.route) {
            HomeMainScreen(
                navController = navController,
                userId = 1 // 여기에 실제 사용자 ID 값 넣기
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
            )
        }
        composable(Route.MealExist.route) {
            MealMainScreen(
                navController = navController,
                selectedTab = "기록",
                onTabClick = {},
                onRecordClick = {},
                onFastedClick = {},
            )
        }
        composable(Route.MealPatch.route) {
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
        composable(Route.MealTime.route) {
            MealTimeScreen(
                navController = navController
            )
        }
        navigation(
            route = "RoutineRecordGraph",
            startDestination = "RoutineRecordEdit"
        ) {
            composable(
                route = "RoutineRecordEdit"
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineRecordGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessRoutineEditScreen(
                    navController = navController,
                    routineViewModel = routineViewModel,
                    selectedTab = "기록",
                    onTabClick = {}
                )
            }
            composable(
                route = "FitnessDetailInput"
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineRecordGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessDetailInputScreen(
                    routineViewModel = routineViewModel,
                    navController = navController
                )
            }
        }
        navigation(
            route = "FitnessAddGraph",
            startDestination = "FitnessRecordSearch"
        ) {
            composable(
                route = "FitnessRecordSearch"
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("FitnessAddGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessRoutineSearchScreen(
                    routineViewModel = routineViewModel,
                    navController = navController,
                )
            }
            composable(
                route = "FitnessAddDetailRecord/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("FitnessAddGraph")
                }
                val vm = hiltViewModel<RoutineViewModel>(parentEntry)
                val nameArg = backStackEntry.arguments?.getString("name") ?: ""

                FitnessAddDetailRecordScreen(
                    navController = navController,
                    name = nameArg,
                    routineViewModel = vm
                )
            }
            composable(
                route = "FitnessAddRecordEdit"
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("FitnessAddGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessAddRecordEditScreen(
                    routineViewModel = routineViewModel,
                    navController = navController,
                )
            }
        }
        navigation(
            route = "RecordEditGraph",
            startDestination = "RecordMain"
        ) {
            composable(
                route = "RecordMain"
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RecordEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessRecordMainScreen(
                    routineViewModel = routineViewModel,
                    navController = navController,
                    selectedTab = "기록",
                    onTabClick = {}
                )
            }
            composable(
                route = Route.FitnessRecordEdit.route
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RecordEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessRecordEditScreen(
                    routineViewModel = routineViewModel,
                    navController = navController,
                )
            }
            composable(
                route = Route.FitnessSearch.route
            ) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RecordEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessSearchScreen(
                    routineViewModel = routineViewModel,
                    navController = navController,
                )
            }
            composable(
                route = "FitnessDetailRecord/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RecordEditGraph")
                }
                val vm = hiltViewModel<RoutineViewModel>(parentEntry)
                val nameArg = backStackEntry.arguments?.getString("name") ?: ""

                FitnessDetailRecordScreen(
                    navController = navController,
                    name = nameArg,
                    routineViewModel = vm
                )
            }
        }
        navigation(
            route = "RoutineEditGraph",
            startDestination = "RoutineEditGraph/RoutineEdit?routineId={routineId}&name={name}"
        ) {
            composable(
                route = "RoutineEditGraph/RoutineEdit?routineId={routineId}&name={name}",
                arguments = listOf(
                    navArgument("routineId") { type = NavType.IntType; defaultValue = -1 },
                    navArgument("name") { type = NavType.StringType; defaultValue = "" }
                )
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineEditGraph")
                }
                val args = backStackEntry.arguments!!
                val routineId = args.getInt("routineId")
                val name = args.getString("name") ?: ""
                parentEntry.savedStateHandle["routineId"] = routineId
                parentEntry.savedStateHandle["name"] = name
                val vm = hiltViewModel<RoutineViewModel>(parentEntry)
                FitnessEditScreen(modifier, navController, vm)
            }
            composable(Route.FitnessCreate.route) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessCreateScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
            composable(Route.FitnessSearch.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parentEntry)
                FitnessSearchScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
            composable(Route.FitnessEdit.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineEditGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parentEntry)
                FitnessEditScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
        }

        navigation(
            route = "DietEditGraph",
            startDestination = "DietEditGraph/DietEditTemp?dietId={dietId}&fwqRaw={fwqRaw}&name={name}"
        ) {
            composable(
                route = "DietEditGraph/DietEditTemp?dietId={dietId}&fwqRaw={fwqRaw}&name={name}",
                arguments = listOf(
                    navArgument("dietId") {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument("fwqRaw") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("name") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietEditGraph")
                }
                val args = backStackEntry.arguments
                val dietId = args?.getInt("dietId") ?: -1
                val fwqRaw = args?.getString("fwqRaw") ?: ""
                val name = args?.getString("name") ?: ""
                parentEntry.savedStateHandle["name"] = name

                parentEntry.savedStateHandle["dietId"] = dietId
                parentEntry.savedStateHandle["fwqRaw"] = fwqRaw
                Log.d("navgraph", "dietId=$dietId,name=$name")

                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietEditTempScreen(
                    navController = navController,
                    dietViewModel = dietViewModel
                )

            }
            composable(
                "dieteditsearch"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietEditGraph")
                }
                DietEditSearchScreen(
                    modifier = modifier,
                    navController = navController,
                )
            }
            composable(
                route = "diet_edit_search_detail/{foodName}"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietEditGraph")
                }
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietEditSearchItemDetailScreen(
                    navController = navController,
                    dietViewModel = dietViewModel,
                    foodName = foodName
                )
            }
            composable("DietEditTemp") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietEditGraph")
                }
                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietEditTempScreen(
                    modifier = modifier,
                    navController = navController,
                    dietViewModel = dietViewModel
                )
            }
        }

        navigation(
            route = "PlanIPGraph",
            startDestination = "PlanIPGraph/plan_in_person_add?date={date}"
        ) {
            composable(
                route = "PlanIPGraph/plan_in_person_add?date={date}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("PlanIpGraph")
                }
                val args = backStackEntry.arguments
                val date = args?.getString("name") ?: ""
                parentEntry.savedStateHandle["date"] = date
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                PlanIPAddScreen(
                    mealViewModel = mealViewModel,
                    navController = navController
                )
            }
            composable(
                route = "PlanIPSearch"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("PlanIpGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                PlanIPSearchScreen(
                    navController = navController
                )
            }
            composable(
                route = "plan_search_detail/{foodName}"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("PlanIPGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                PlanItemScreen(
                    navController = navController,
                    mealViewModel = mealViewModel,
                    foodName = foodName
                )
            }
            composable(
                route = "PlanIPTemp"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("PlanIpGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                PlanIPTempScreen(
                    mealViewModel = mealViewModel,
                    navController = navController
                )
            }
        }

        navigation(
            route = "MealEditGraph",
            startDestination = "MealEditGraph/MealEditTemp?dietId={dietId}&fwqRaw={fwqRaw}&mealType={mealType}"
        ) {
            composable(
                route = "MealEditGraph/MealEditTemp?dietId={dietId}&fwqRaw={fwqRaw}&mealType={mealType}",
                arguments = listOf(
                    navArgument("dietId") {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument("fwqRaw") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("mealType") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealEditGraph")
                }
                val args = backStackEntry.arguments
                val dietId = args?.getInt("dietId") ?: -1
                val fwqRaw = args?.getString("fwqRaw") ?: ""
                val mealType = args?.getString("mealType") ?: ""
                parentEntry.savedStateHandle["mealType"] = mealType

                parentEntry.savedStateHandle["dietId"] = dietId
                parentEntry.savedStateHandle["fwqRaw"] = fwqRaw

                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)

                MealEditTempScreen(
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
            composable(Route.MealEditTemp.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealEditGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealEditTempScreen(
                    modifier = modifier,
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }

            composable(
                "mealeditsearch"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealEditGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealEditSearchScreen(navController = navController, mealViewModel = mealViewModel)
            }
            composable(
                route = "meal_edit_search_detail/{foodName}",
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealEditGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                MealEditSearchItemDetailScreen(
                    foodName = foodName,
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
            composable(Route.MealTime.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealEditGraph")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealEditTimeScreen(
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
        }
        /*navigation(
            route = "RoutineRecordGraph",
            startDestination = "FitnessRecordMain"
        ){
            composable("FitnessRecordMain")
        }*/
        composable(
            route = "FitnessRecordMain"
        ) {
            FitnessRecordMainScreen(
                navController = navController,
                selectedTab = "기록",
                onTabClick = {}
            )
        }
        composable(route = "FitnessRecord") {
            FitnessRecordScreen(
                navController = navController
            )
        }
        navigation(
            route = "RoutineGraph",
            startDestination = Route.FitnessCreate.route
        ) {
            composable(Route.FitnessCreate.route) { backStackEntry ->
                val parenEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parenEntry)
                FitnessCreateScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
            composable(Route.FitnessSearch.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parentEntry)
                FitnessSearchScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
            composable(Route.FitnessEdit.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("RoutineGraph")
                }
                val routineViewModel = hiltViewModel<RoutineViewModel>(parentEntry)
                FitnessEditScreen(
                    navController = navController,
                    routineViewModel = routineViewModel
                )
            }
        }

        navigation(
            route = "DietGraph",
            startDestination = Route.DietCreate.route
        ) {
            composable(Route.DietCreate.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietGraph")
                }
                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietCreateScreen(
                    modifier = modifier,
                    navController = navController,
                    dietViewModel = dietViewModel
                )
            }
            composable(
                Route.DietSearch.route
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietGraph")
                }
                DietSearchScreen(
                    modifier = modifier,
                    navController = navController,
                )
            }
            composable(
                route = "diet_search_detail/{foodName}"
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietGraph")
                }
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietSearchItemDetailScreen(
                    navController = navController,
                    dietViewModel = dietViewModel,
                    foodName = foodName
                )
            }
            composable(Route.DietPatch.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("DietGraph")
                }
                val dietViewModel = hiltViewModel<DietViewModel>(parentEntry)
                DietPatchScreen(
                    modifier = modifier,
                    navController = navController,
                    dietViewModel = dietViewModel
                )
            }
        }

        navigation(
            route = "MealGraph/{mealType}",
            startDestination = Route.MealRecord.route,
            arguments = listOf(navArgument("mealType") { defaultValue = "" })
        ) {
            composable(
                Route.MealRecord.route
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealGraph/{mealType}")
                }
                val mealType = parentEntry.arguments?.getString("mealType") ?: ""
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealRecordScreen(
                    navController = navController
                )
            }
            composable(
                Route.MealPatch.route
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealGraph/{mealType}")
                }
                val mealType = parentEntry.arguments?.getString("mealType") ?: ""
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealPatchScreen(
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
            composable(
                Route.MealSearch.route
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealGraph/{mealType}")
                }
                val mealType = parentEntry.arguments?.getString("mealType") ?: ""
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealSearchScreen(navController = navController, mealViewModel = mealViewModel)
            }
            composable(
                route = "meal_search_detail/{foodName}",
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealGraph/{mealType}")
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
                    navController.getBackStackEntry("MealGraph/{mealType}")
                }
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)
                MealTempScreen(navController = navController, mealViewModel = mealViewModel)
            }
            composable(Route.MealTime.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("MealGraph/{mealType}")
                }
                val mealType = parentEntry.arguments?.getString("mealType") ?: ""
                val mealViewModel = hiltViewModel<MealViewModel>(parentEntry)

                LaunchedEffect(mealType) {
                    mealViewModel.setType(mealType)
                }
                MealTimeScreen(
                    navController = navController,
                    mealViewModel = mealViewModel
                )
            }
        }


//        composable(Route.HomeAnalysis.route) {
//            HomeAnalysisScreen(
//                modifier = modifier
//            )
//        }

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
            )
        }
        composable(Route.FitnessEditResult.route) {
            FitnessEditResultScreen(navController = navController)
        }
        composable(Route.FitnessSearch.route) {
            FitnessSearchScreen(
                navController = navController
            )
        }
        composable(route = Route.Fitness.route) {
            FitnessMainScreen(
                navController = navController,
                onFastedClick = { /* 구현 */ },
                onRecordClick = { /* 구현 */ },
                selectedTab = "0", // Int면 타입 맞춰서 수정
                onTabClick = { /* 탭 클릭 시 동작 */ }
            )
        }
        composable(route = Route.FitnessExist.route) {
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
            )
        }
        composable(route = Route.FitnessRecordEdit.route) {
            FitnessRecordEditScreen(
                navController = navController
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
            )
        }

        composable("fitness/detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            FitnessDetailRecordScreen(
                navController = navController,
                name = name
            )
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
//        composable(route = FitnessDetailInput.route) {
//            val viewModel: FitnessViewModel = hiltViewModel()
//            FitnessDetailInputScreen(
//                fitnessList = viewModel.selectedFitnessList // ViewModel에서 가져옴
//            )
//        }


//온보딩
        composable(OnboardingDiet.route) {
            OnboardingDietScreen(navController = navController) // 필요 시 navController 넘기세요
        }
        composable(OnboardingFailEx.route) {
            OnboardingFailExScreen(navController = navController)
        }
        composable(OnboardingAppetite.route) {
            OnboardingAppetiteScreen(navController = navController)
        }
        composable(OnboardingWeek.route) {
            OnboardingWeekScreen(navController = navController)
        }
        composable(OnboardingPreferType.route) {
            OnboardingPreferTypeScreen(navController = navController)
        }

        composable(route = OnboardingDietSpeed.route) {
            OnboardingDietSpeedScreen(
                navController = navController,
                onNavigate = { selectedMode ->
                    navController.navigate("${OnboardingActivityLevel.route}/$selectedMode")
                }
            )
        }

        composable(
            route = "${OnboardingActivityLevel.route}/{mode}",
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedMode = backStackEntry.arguments?.getString("mode") ?: ""
            OnboardingActivityLevelScreen(
                navController = navController,
                selectedMode = selectedMode
            )
        }

        composable(
            route = "${OnboardingInput.route}/{mode}",
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedMode = backStackEntry.arguments?.getString("mode") ?: ""
            OnboardingInputScreen(
                navController = navController,
                selectedMode = selectedMode
            )
        }


        composable(route = OnboardingInputResult.route) {
            OnboardingInputResultScreen(navController = navController)
        }
        composable(route = OnboardingIntroduce.route) {
            OnboardingIntroduceScreen(navController = navController)
        }
        composable(route = OnboardingHamCoach.route) {
            OnboardingHamCoachScreen(navController = navController)
        }
        composable(route = OnboardingNyamCoach.route) {
            OnboardingNyamCoachScreen(navController = navController)
        }
        composable(route = OnboardingDelivery.route) {
            OnboardingDeliveryScreen(navController = navController)
        }
        composable(route = OnboardingMainHomeHam.route) {
            OnboardingMainHomeHamScreen(navController = navController)
        }
        composable(route = OnboardingMainHomeNyam.route) {
            OnboardingMainHomeNyamScreen(navController = navController)
        }
        composable(route = OnboardingMainHomeScale.route) {
            OnboardingMainHomeScaleScreen(navController = navController)
        }

        composable(OnboardingAiMeal.route) {
            OnboardingAiMealScreen(navController = navController)
        }
        composable(OnboardingMeal.route) {
            OnboardingMealScreen(navController = navController)
        }
        composable(OnboardingAiIntro.route) {
            OnboardingAiIntroScreen(navController = navController)
        }
        composable(OnboardingGray.route) {
            OnboardingGrayScreen(navController = navController)
        }
        composable(OnboardingYellow.route) {
            OnboardingYellowScreen(navController = navController)
        }
        composable(OnboardingEoDrink.route) {
            OnboardingEoDrinkScreen(navController = navController)
        }
        composable(OnboardingCheck.route) {
            OnboardingCheckScreen(navController = navController)
        }
        composable(OnboardingFinal.route) {
            OnboardingFinalScreen(navController = navController)
        }
        composable(OnboardingFloatingButton.route) {
            OnboardingFloatingButtonScreen(navController = navController)
        }

        // 로그인
        composable(route = Route.LoginMain.route) {
            LoginMainScreen(navController = navController)
        }
        composable(route = Route.LoginEmail.route) {
            LoginEmailScreen(navController = navController)
        }


    }
}

