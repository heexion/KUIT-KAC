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
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.FoodDatabase
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
                    Route.FitnessEditResult.route
                )
                val backArrowRoutes = setOf(
                    // 여기다가 뒤로가기 버튼 있으면 추가
                    Route.HomeNutrition.route,
//                    Route.HomeAnalysis.route,
                    Route.HomeObservation.route,
                    Route.HomeScale.route,
                    Route.DietCreate.route,
                    "plan_ai_detail",
                    Route.PlanCheck.route,
                    Route.MealSearch.route,
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
                                .clickable { navController.navigate(Route.PlanDiet.route) },
                            painter = painterResource(id = R.drawable.ic_navigate_plan),
                            contentDescription = "",
                        )
                    }

                }

            }

        }

        lifecycleScope.launch {
            if(!foodDao.hasAnyFood()){
                val foods = loadFood(applicationContext)
                foodDao.insertAll(foods)
            }
        }
        lifecycleScope.launch {
            if(!fitnessDao.hasAnyFitness()){
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