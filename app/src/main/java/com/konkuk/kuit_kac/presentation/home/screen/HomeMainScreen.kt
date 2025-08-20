package com.konkuk.kuit_kac.presentation.home.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.Loading
import com.konkuk.kuit_kac.component.NyamNyamNyom
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeNutritionCircleGraph
import com.konkuk.kuit_kac.presentation.home.component.HomeSummaryBox
import com.konkuk.kuit_kac.presentation.home.component.NyameeGif
import com.konkuk.kuit_kac.presentation.home.viewmodel.HomeSummaryViewModel
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import kotlin.random.Random

@Composable
fun HomeMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userId: Int,
    viewModel: HomeSummaryViewModel = hiltViewModel()
) {
    var randNum by remember { mutableIntStateOf(1) }
    val isLate = true
    var hamcoachNum by remember { mutableIntStateOf(1) }
    LaunchedEffect(Unit) {
        viewModel.getSummary(userId)
        if (!isLate) randNum = Random.nextInt(3) + 1
        else {
            randNum = 4
            hamcoachNum = 3
        }
    }
    val summary by viewModel.summary

    Log.d("HomeMainScreen", summary?.dailyKCalorieGoal.toString())


    // 로딩 상태 처리
    /*if (summary == null) {
        Loading()
        return
    }*/

    // API 응답에서 값 추출
    val goal = summary?.dailyKCalorieGoal ?: 0
    val current = summary?.weight ?: 0
    val left = summary?.remainingKCalorie ?: 0

    HomeBackgroundComponent()
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
        ) {
            HamcoachGif(
                modifier = Modifier.offset(x = 24f.wp(), y = 72.18f.hp()),
                onClick = {
                    navController.navigate(Route.HomeObservation.route)
                },
                num = hamcoachNum
            )

            NyameeGif(
                onClick = {
                    navController.navigate(Route.HomeNutrition.route)
                },
                modifier = Modifier.offset(x = 60f.wp(), y = 60f.bhp()),
                num = randNum,
                sizePercent = 0.93f
            )

            Box(
                modifier = Modifier
                    .size(248.0013f.wp(), 103.00002f.bhp())
                    .offset(x = 144f.wp(), y = 40f.hp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .matchParentSize(),
                    painter = painterResource(R.drawable.img_homegraphscreen_maintextballoon),
                    contentDescription = "너 진짜 너무 많이 먹은 거 아냐 ㅠㅠ"
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 29.89f.bhp()),
                    text = "너 진짜 ,,,, ㅠㅠ\n넘 많이 먹은 거 아냐?",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }

            Image(
                modifier = Modifier
                    .size(94.13432f.wp(), 53f.bhp())
                    .offset(y = 311f.hp(), x = 68.38f.wp())
                    .noRippleClickable {
                        navController.navigate(Route.HomeScale.route)
                    },
                painter = painterResource(R.drawable.img_home_weight),
                contentDescription = "weight measurer"
            )
        }

        // 하단 요약 UI
        Column(
            modifier = Modifier
                .padding(top = 377f.hp())
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                    )
                )
                .border(1.dp, color = Color(0xFF000000), RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                .clickable (
                    onClick = {
                        navController.navigate("NyamNyamNyom")
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 42f.bhp()),
                text = "오늘 남은 칼로리",
                color = Color(0xFF000000),
                style = DungGeunMo20,
                fontSize = 20f.isp()
            )
            Text(
                modifier = Modifier.padding(top = 7.61f.bhp()),
                text = "${left.toInt()}kcal",
                style = DungGeunMo35,
                fontSize = 35f.isp(),
                color = Color(0xFFFFA100)
            )
            Row(
                modifier = Modifier.padding(top = 24.39f.bhp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(19.86f.wp())
            ) {
                HomeNutritionCircleGraph(goal = goal.toInt(), left = left.toInt())
                Column {
                    HomeSummaryBox(
                        title = "목표 일일 칼로리",
                        value = "${goal.toInt()}kcal",
                        width = 154f.wp(),
                        height = 70f.bhp()
                    )
                    HomeSummaryBox(
                        modifier = Modifier.padding(top = 12f.bhp()),
                        title = "현재 체중",
                        value = "${current}kg",
                        width = 154f.wp(),
                        height = 70f.bhp()
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeMainScreenPreview() {
//    val navController = rememberNavController()
//    HomeMainScreen(current = 55, goal = 2300, left = 800, navController = navController)
//}
