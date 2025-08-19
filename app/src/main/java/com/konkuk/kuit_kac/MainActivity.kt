package com.konkuk.kuit_kac

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.local.dao.FitnessDao
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.parse.loadFitness
import com.konkuk.kuit_kac.local.parse.loadFood
import com.konkuk.kuit_kac.notification.isNotificationServiceEnabled
import com.konkuk.kuit_kac.presentation.component.BottomBar
import com.konkuk.kuit_kac.presentation.navigation.KacNavGraph
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.KUITKACTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var foodDao: FoodDao

    @Inject
    lateinit var fitnessDao: FitnessDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val requestPostNoti = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (!granted) {
                Toast.makeText(this, "알림 권한이 필요해요.", Toast.LENGTH_SHORT).show()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPostNoti.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                runCatching {
                    startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = Uri.parse("package:$packageName")
                    })
                }
            }
        }
        val notificationsEnabled = NotificationManagerCompat.from(this).areNotificationsEnabled()
        if (!notificationsEnabled) {
            Toast.makeText(this, "앱 알림이 꺼져 있어요. 설정에서 켜주세요!", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }
            startActivity(intent)
        }
        if (!isNotificationServiceEnabled(this)) {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "alert_channel",
                "Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Shows alerts when 주문 접수 is detected"
            }
            NotificationManagerCompat.from(this).createNotificationChannel(channel)
        }

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
                    Route.FitnessEditResult.route,
                    Route.LoginMain.route,
                    Route.LoginEmail.route,

                    // 온보딩 화면들
                    Route.OnboardingStart.route,
                    Route.OnboardingDiet.route,
                    Route.OnboardingFailEx.route,
                    Route.OnboardingAppetite.route,
                    Route.OnboardingWeek.route,
                    Route.OnboardingPreferType.route,
                    Route.OnboardingDietSpeed.route,
                    Route.OnboardingActivityLevel.route,
                    Route.OnboardingInputResult.route,
                    Route.OnboardingIntroduce.route,
                    Route.OnboardingHamCoach.route,
                    Route.OnboardingNyamCoach.route,
                    Route.OnboardingDelivery.route,
                    Route.OnboardingMainHomeHam.route,
                    Route.OnboardingMainHomeNyam.route,
                    Route.OnboardingMainHomeScale.route,

                    Route.OnboardingAiMeal.route,
                    Route.OnboardingMeal.route,
                    Route.OnboardingAiIntro.route,
                    Route.OnboardingGray.route,
                    Route.OnboardingYellow.route,
                    Route.OnboardingEoDrink.route,
                    Route.OnboardingCheck.route,
                    Route.OnboardingFinal.route,
                    Route.OnboardingFloatingButton.route
                )

                val shouldHideBottomBar =
                    hideBottomBarRoutes.any { currentRoute?.startsWith(it) == true } ||
                            currentRoute?.startsWith("${Route.OnboardingInput.route}/") == true


                val backArrowRoutes = setOf(
                    // 홈 관련
                    Route.HomeNutrition.route,
                    Route.HomeObservation.route,
                    Route.HomeScale.route,
                    Route.HomeScaleInput.route,
                    Route.HomeResult.route,

                    // 식단/식사 관련
                    Route.DietCreate.route,
                    Route.DietExist.route,
                    Route.DietPatch.route,
                    Route.DietSearch.route,
                    Route.MealTime.route,
                    Route.MealRecord.route,
                    Route.MealExist.route,
                    Route.MealPatch.route,
                    Route.MealFastingResult.route,
                    Route.MealSearch.route,
                    Route.MealTemp.route,
                    Route.MealEditTemp.route,

                    Route.MealEditResult.route,
                    Route.MealEditSearchItem.route,
                    Route.MealEditSearch.route,
                    Route.MealEditTime.route,
                    Route.MealMain.route,
                    Route.MealSearchItemDetail.route,
                    Route.TimeInputResult.route,

                    Route.DietEditSearchItem.route,
                    Route.DietEditSerch.route,
                    Route.DietEditTemp.route,
                    Route.DietMain.route,
                    Route.DietSearchItemDetail.route,

                    // 식단 계획 관련
                    Route.PlanDiet.route,
                    Route.PlanAI.route,
                    Route.PlanInPerson.route,
                    Route.PlanCheck.route,
                    "plan_ai_detail",

                    // 운동 관련
                    Route.Fitness.route,
                    Route.FitnessExist.route,
                    Route.FitnessCreate.route,
                    Route.FitnessSearch.route,
                    Route.FitnessEdit.route,
                    Route.FitnessEditResult.route,
                    Route.FitnessRecordEdit.route,
                    Route.FitnessRoutineEdit.route,
                    Route.FitnessRecordResult.route,
                    Route.FitnessRoutineSearch.route,
                    Route.FitnessFastInput.route,
                    Route.FitnessDetailRecordAdd.route,
                    Route.FitnessDetailInput.route,
                    Route.FitnessRecordScreen.route,
                    Route.FitnessDetailRecord("sample").route,
                    Route.FitnessAddDetailRecord.route,
                    Route.FitnessAddRecordEdit.route,
                    Route.FitnessRecordMain.route,
                    Route.FitnessRoutineDetailInput.route,


                    // 온보딩 관련
                    Route.OnboardingDiet.route,
                    Route.OnboardingFailEx.route,
                    Route.OnboardingAppetite.route,
                    Route.OnboardingWeek.route,
                    Route.OnboardingPreferType.route,
                    Route.OnboardingDietSpeed.route,
                    "${Route.OnboardingActivityLevel.route}/{mode}",
                    "${Route.OnboardingInput.route}/{mode}",
                    Route.OnboardingInputResult.route,
                    Route.OnboardingIntroduce.route,
                    Route.OnboardingHamCoach.route,
                    Route.OnboardingNyamCoach.route,
                    Route.OnboardingDelivery.route,
                    Route.OnboardingMainHomeHam.route,
                    Route.OnboardingMainHomeNyam.route,
                    Route.OnboardingMainHomeScale.route,
                    Route.OnboardingAiMeal.route,
                    Route.OnboardingMeal.route,
                    Route.OnboardingAiIntro.route,
                    Route.OnboardingGray.route,
                    Route.OnboardingYellow.route,
                    Route.OnboardingEoDrink.route,
                    Route.OnboardingCheck.route,
                    Route.OnboardingFinal.route,
                    Route.OnboardingFloatingButton.route,

                    // 로그인 관련
                    Route.LoginEmail.route
                )

                val planButtonRoutes = setOf(
                    Route.Home.route,
                    Route.HomeNutrition.route,
//                    Route.HomeAnalysis.route,
                    Route.HomeObservation.route,
                    Route.Diet.route,
                    Route.DietExist.route,
                    Route.DietCreate.route,
                    Route.Fitness.route,
                    Route.FitnessExist.route,
                    Route.MealExist.route
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (!shouldHideBottomBar) {
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
                            if (!shouldHideBottomBar) {
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
                                .noRippleClickable { navController.popBackStack() },
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "",
                        )
                    }

                    if (currentRoute in planButtonRoutes) {
                        Image(
                            modifier = Modifier
                                .padding(end = 25f.wp(), bottom = 93f.bhp())
                                .size(61f.wp(), 61f.bhp())
                                .align(Alignment.BottomEnd)
                                .noRippleClickable {
                                    navController.navigate(Route.PlanDiet.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = false
                                        }
                                        launchSingleTop = true
                                    }
                                },
                            painter = painterResource(id = R.drawable.ic_navigate_plan),
                            contentDescription = "",
                        )
                    }

                }

            }

        }

        lifecycleScope.launch {
            if (!foodDao.hasAnyFood()) {
                val foods = loadFood(applicationContext)
                foodDao.insertAll(foods)
            }
        }
        lifecycleScope.launch {
            if (!fitnessDao.hasAnyFitness()) {
                val fitnesses = loadFitness(applicationContext)
                fitnessDao.insertAll(fitnesses)
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