package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeNutritionCircleGraph
import com.konkuk.kuit_kac.presentation.home.component.HomeSummaryBox
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35

@Composable
fun HomeMainScreen(
    modifier: Modifier = Modifier,
    goal: Int,
    current: Int,
    left: Int,
    navController: NavHostController
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
        ) {
            HomeBackgroundComponent()
            EllipseNyam(modifier = Modifier
                .offset(
                    x = 30.dp,
                    y = 139.04.dp
                )
                .clickable(
                    onClick = {
                        navController.navigate(Route.HomeObservation.route)
                    }
                ),
                ellipseLength = 147.6658,
                mascotLength = 88.43783
            )
            Box(
                modifier = Modifier
                    .size(248.0013.dp, 103.00002.dp)
                    .offset(x = 144.dp, y = 80.dp)
            ){
                Image(
                    modifier = Modifier
                        .matchParentSize(),
                    painter = painterResource(R.drawable.img_homegraphscreen_maintextballoon),
                    contentDescription = "너 진짜 너무 많이 먹은 거 아냐 ㅠㅠ"
                )
                Text(
                    modifier = Modifier
                        .offset(x = 34.27.dp, y = 18.11.dp),
                    text = "너 진짜 ,,,, ㅠㅠ\n넘 많이 먹은 거 아냐?",
                    style = DungGeunMo17,
                    textAlign = TextAlign.Center
                )
            }

            Image(
                modifier = Modifier
                    .size(187.67596.dp,244.dp)
                    .offset(
                        x = 160.dp,
                        y = 164.dp
                    )
                    .clickable(
                        onClick = {
                            navController.navigate(Route.HomeNutrition.route)
                        }
                    ),
                painter = painterResource(R.drawable.img_main_person),
                contentDescription = "you person"
            )
            Image(
                modifier = Modifier
                    .size(94.13432.dp, 53.dp)
                    .offset(y = 351.dp, x = 68.38.dp)
                    .clickable(
                        onClick = {
                            navController.navigate(Route.HomeScale.route)
                        }
                    ),
                painter = painterResource(R.drawable.img_home_weight),
                contentDescription = "weight measurer"
            )
        }

        // 밑에 남은 칼로리 화면
        Column(
            modifier = Modifier
                .padding(top = 417.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(
                    topStart = 75.dp,
                    topEnd = 75.dp
                ))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF,),Color(0xFFFFEDD0))
                    )
                )
                .border(1.dp, color = Color(0xFF000000), RoundedCornerShape(
                    topStart = 75.dp, topEnd = 75.dp
                )),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 42.dp),
                text = "오늘 남은 칼로리",
                style = DungGeunMo20
            )
            Text(
                modifier = Modifier
                    .padding(top = 7.61.dp),
                text = "300kcal",
                style = DungGeunMo35,
                color = Color(0xFFFFA100)
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = 24.39.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(19.86.dp)
            ) {
                HomeNutritionCircleGraph(
                    current = current,
                    goal = goal,
                    left = left
                )
                Column(
                    modifier = Modifier,
                ) {
                    HomeSummaryBox(
                        title = "목표 일일 칼로리",
                        value = goal.toString() + "kcal"
                    )
                    HomeSummaryBox(
                        modifier = Modifier
                            .padding(top = 12.dp),
                        title = "현재 체중",
                        value = current.toString() + "kg"
                    )
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun HomeMainScreenPreview(){
    val navController = rememberNavController()
    HomeMainScreen(current = 55, goal = 2300, left = 800, navController = navController)
}