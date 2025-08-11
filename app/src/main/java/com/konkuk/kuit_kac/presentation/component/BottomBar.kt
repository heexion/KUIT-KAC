package com.konkuk.kuit_kac.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route

@Composable
fun BottomBar(
    navController: NavController
) {
    val selectedRoute = remember { mutableStateOf(Route.Home.route) }
    val density = LocalDensity.current

    // 화면 가로폭 구하기
    val screenWidthPx = LocalConfiguration.current.screenWidthDp.dp
    val imageHeightDp = screenWidthPx * (141f / 415f) // 비율 유지

    // 선택된 메뉴에 따른 배경 이미지
    val backgroundRes = when (selectedRoute.value) {
        Route.Home.route -> R.drawable.img_bottomhome
        Route.Diet.route -> R.drawable.img_bottommeal
        Route.Fitness.route -> R.drawable.img_bottomfitness
        else -> R.drawable.img_bottombar
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeightDp)
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // 클릭 영역
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val widthPx = size.width
                        val x = offset.x

                        when {
                            x < widthPx / 3 -> {
                                selectedRoute.value = Route.Diet.route
                                navController.navigate(Route.Diet.route)
                            }
                            x < widthPx * 2 / 3 -> {
                                selectedRoute.value = Route.Home.route
                                navController.navigate(Route.Home.route)
                            }
                            else -> {
                                selectedRoute.value = Route.Fitness.route
                                navController.navigate(Route.Fitness.route)
                            }
                        }
                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomBar( navController = navController)
}
