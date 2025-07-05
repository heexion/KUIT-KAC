package com.konkuk.kuit_kac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.presentation.component.BottomBar
import com.konkuk.kuit_kac.presentation.navigation.KacNavGraph
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.KUITKACTheme

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

                val hideBottomBarRoutes = setOf(Route.HomeScaleInput.route, Route.HomeResult.route)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = WindowInsets.navigationBars
                                .asPaddingValues()
                                .calculateBottomPadding()
                        )
                ){
                    Scaffold(
                        bottomBar = {
                            // 특정 화면들에서 bottomBar 보이지 않게 설정
                            if (currentRoute !in hideBottomBarRoutes) {
                                BottomBar(
                                    navController
                                )
                            }
                        },
                    ){ innerPadding ->
                        KacNavGraph(
                            modifier = Modifier
                                .padding(innerPadding),
                            navController = navController
                        )
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