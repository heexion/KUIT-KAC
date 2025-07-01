package com.konkuk.kuit_kac.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun BottomBar(navController: NavController) {
    // 현재 선택된 라우트
    val selectedRoute = remember { mutableStateOf(Route.Home.route) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        // 메인 Canvas
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .align(Alignment.BottomCenter)
        ) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val radius = 40.dp.toPx()
            val cornerRadius = 10.dp.toPx()

            val mainPath = Path().apply {
                moveTo(0f, cornerRadius)
                arcTo(
                    Rect(0f, 0f, cornerRadius * 2, cornerRadius * 2),
                    180f,
                    90f,
                    false
                )
                lineTo(centerX - radius - cornerRadius, 0f)
                quadraticBezierTo(
                    centerX - radius, 0f,
                    centerX - radius, cornerRadius
                )
                arcTo(
                    Rect(
                        centerX - radius,
                        -radius + cornerRadius,
                        centerX + radius,
                        radius + cornerRadius
                    ),
                    180f,
                    -180f,
                    false
                )
                quadraticBezierTo(
                    centerX + radius, 0f,
                    centerX + radius + cornerRadius, 0f
                )
                lineTo(width - cornerRadius, 0f)
                arcTo(
                    Rect(width - cornerRadius * 2, 0f, width, cornerRadius * 2),
                    270f,
                    90f,
                    false
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = mainPath,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF3B3), Color(0xFFFFEC8C))
                ),
                style = Fill
            )
            drawPath(
                path = mainPath,
                color = Color.Black,
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 32.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 식단 버튼
            Box(
                modifier = Modifier
                    .offset(x = (-24).dp) // 왼쪽으로 12dp 이동
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        selectedRoute.value = Route.Diet.route
                        navController.navigate(Route.Diet.route)
                    }
                    .height(56.dp)
                    .width(160.dp)
            ) {
                if (selectedRoute.value == Route.Diet.route) {
                    Image(
                        painter = painterResource(R.drawable.bg_button_diet_selected),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = 1.8f,  // 가로만 20% 늘림
                                scaleY = 1.0f
                            )
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_diet),
                        contentDescription = "식단",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("식단", style = DungGeunMo17)
                }
            }

            Spacer(modifier = Modifier.width(24.dp))

// 운동 버튼
            Box(
                modifier = Modifier
                    .offset(x = (24).dp) // 오른쪽으로 12dp 이동
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        selectedRoute.value = Route.Fitness.route
                        navController.navigate(Route.Fitness.route)
                    }
                    .height(56.dp)
                    .width(160.dp)
            ) {
                if (selectedRoute.value == Route.Fitness.route) {
                    Image(
                        painter = painterResource(R.drawable.bg_button_fitness_selected),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = 1.8f,  // 가로만 20% 늘림
                                scaleY = 1.0f
                            )
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_fitness),
                        contentDescription = "운동",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("운동", style = DungGeunMo17)
                }
            }


        }

        // 중앙 홈 버튼
        Box(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-20).dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFF3B3),
                            Color(0xFFFFEC8C)
                        )
                    )
                )
                .border(2.dp, Color.Black, CircleShape)
                .clickable {
                    selectedRoute.value = Route.Home.route
                    navController.navigate(Route.Home.route)
                },
            contentAlignment = Alignment.Center
        ) {
            if (selectedRoute.value == Route.Home.route) {
                Image(
                    painter = painterResource(R.drawable.bg_button_home_selected),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp) //원 안에 작게 들어가도록 여백 주기
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Text("홈", style = DungGeunMo17)
        }

    }
    }


@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomBar(navController = navController)
}
