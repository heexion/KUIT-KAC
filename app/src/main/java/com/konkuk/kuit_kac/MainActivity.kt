package com.konkuk.kuit_kac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.FoodDatabase
import com.konkuk.kuit_kac.local.parse.loadFood
import com.konkuk.kuit_kac.presentation.component.BottomBar
import com.konkuk.kuit_kac.presentation.navigation.KacNavGraph
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.KUITKACTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightNavigationBars = true
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
        }

        setContent {
            KUITKACTheme {
                val navController = rememberNavController()
                val currentRoute = navController
                    .currentBackStackEntryAsState()
                    .value?.destination?.route
                val hideBottomBarRoutes = setOf(
                    Route.HomeScaleInput.route,
                    Route.HomeResult.route,
                    Route.MealFastingResult.route,
                    "time_input_result",
                    "meal_edit_result",
                    "plan_ai_loading",
                    "plan_result",
                    Route.FitnessEditResult.route
                )
                val backArrowRoutes = setOf(
                    // 여기다가 뒤로가기 버튼 있으면 추가
                    Route.HomeNutrition.route,
                    Route.HomeAnalysis.route,
                    Route.HomeObservation.route,
                    Route.HomeScale.route,
                    Route.DietCreate.route,
                    "plan_ai_detail",
                    Route.PlanCheck.route,
                    Route.MealSearch.route,
                    Route.DietAdd.route,
                    Route.MealTime.route,
                    Route.FitnessEdit.route,
                    Route.FitnessSearch.route,
                    Route.FitnessCreate.route,
                    Route.MealPatch.route,
                    Route.DietPatch.route
                )
                val planButtonRoutes = setOf(
                    Route.Home.route,
                    Route.HomeNutrition.route,
                    Route.HomeAnalysis.route,
                    Route.HomeObservation.route,
                    Route.Diet.route,
                    Route.DietExist.route,
                    Route.DietCreate.route,
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (currentRoute !in hideBottomBarRoutes) {
                                Modifier.padding(
                                    bottom = WindowInsets.navigationBars
                                        .asPaddingValues()
                                        .calculateBottomPadding()
                                )
                            } else {
                                Modifier
                            }
                        )
                ) {

                    Scaffold(
                        bottomBar = {
                            // 특정 화면들에서 bottomBar 보이지 않게 설정
                            if (currentRoute !in hideBottomBarRoutes) {
                                BottomBar(
                                    navController
                                )
                            }
                        },
                    ) { innerPadding ->
                        KacNavGraph(
                            modifier = Modifier
                                .padding(innerPadding),
                            navController = navController
                        )
                    }
                    if (currentRoute in backArrowRoutes) {
                        Image(
                            modifier = Modifier
                                .padding(start = 24.dp, top = 58.dp)
                                .size(24.dp)
                                .align(Alignment.TopStart)
                                .clickable { navController.popBackStack() },
                            painter = painterResource(id = R.drawable.ic_back_arow),
                            contentDescription = "",
                        )
                    }

                    if (currentRoute in planButtonRoutes) {
                        Image(
                            modifier = Modifier
                                .padding(end = 25f.wp(), bottom = 93f.bhp())
                                .size(61f.wp(), 61f.bhp())
                                .align(Alignment.BottomEnd)
                                .clickable { navController.navigate(Route.PlanDiet.route) },
                            painter = painterResource(id = R.drawable.ic_navigate_plan),
                            contentDescription = "",
                        )
                    }

                }

            }

        }
        val foodDB = Room.databaseBuilder(
            applicationContext,
            FoodDatabase::class.java,
            "food.db"
        ).build()

        val foodData = foodDB.foodDao()
        lifecycleScope.launch {
            if(!foodData.hasAnyFood()){
                val foods = loadFood(applicationContext)
                foodData.insertAll(foods)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KUITKACTheme {
        Greeting("Android")
    }
}