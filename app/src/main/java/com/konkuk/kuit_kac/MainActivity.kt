package com.konkuk.kuit_kac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.presentation.component.BottomBar
import com.konkuk.kuit_kac.presentation.diet.DietScreen
import com.konkuk.kuit_kac.presentation.fitness.FitnessScreen
import com.konkuk.kuit_kac.presentation.home.HomeObservationScreen
import com.konkuk.kuit_kac.presentation.home.HomeScaleScreen
import com.konkuk.kuit_kac.presentation.home.HomeScreen
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.KUITKACTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KUITKACTheme {
                val navController = rememberNavController()
                val currentRoute = navController
                    .currentBackStackEntryAsState()
                    .value?.destination?.route

                val hideBottomBarRoutes = setOf(Route.HomeScale.route)

                Scaffold(
                    modifier = Modifier
                        .systemBarsPadding(),
                    bottomBar = {
                        // 특정 화면들에서 bottomBar 보이지 않게 설정
                        if (currentRoute !in hideBottomBarRoutes) {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Route.HomeScale.route,
                    ) {
                        composable(Route.Home.route) {
                            HomeScreen()
//                            HomeObservationScreen()
                        }
                        composable(Route.HomeScale.route) {
//                            HomeScreen()
//                            HomeObservationScreen()
                            HomeScaleScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(Route.Diet.route) {
                            DietScreen(
                                modifier = Modifier.padding(innerPadding)

                            )
                        }
                        composable(Route.Fitness.route) {
                            FitnessScreen(
                                modifier = Modifier.padding(innerPadding)

                            )
                        }
                    }
                }

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