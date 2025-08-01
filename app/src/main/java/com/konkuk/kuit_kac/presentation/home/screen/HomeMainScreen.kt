package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.GifImage
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeNutritionCircleGraph
import com.konkuk.kuit_kac.presentation.home.component.HomeSummaryBox
import com.konkuk.kuit_kac.presentation.home.component.NyameeGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import kotlin.random.Random

@Composable
fun HomeMainScreen(
    modifier: Modifier = Modifier,
    goal: Int,
    current: Int,
    left: Int,
    navController: NavHostController
) {

    var randNum by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit) {
        randNum = Random.nextInt(3) + 1
    }

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
                })

            NyameeGif(
                onClick = {
                    navController.navigate(Route.HomeNutrition.route)
                },
                modifier = Modifier.offset(x = 75f.wp(), y = 36f.bhp()),
                num = randNum
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
                        .padding(
                            bottom = 29.89f.bhp()
                        ),
                    //.offset(x = 178.27f.wp(), y = 58.11f.hp()),
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
                .padding(top = 377f.hp())
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 75f.wp(),
                        topEnd = 75f.wp()
                    )
                )
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                    )
                )
                .border(
                    1.dp, color = Color(0xFF000000), RoundedCornerShape(
                        topStart = 75f.wp(), topEnd = 75f.wp()
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 42f.bhp()),
                text = "오늘 남은 칼로리",
                color = Color(0xFF000000),
                style = DungGeunMo20,
                fontSize = 20f.isp()
            )
            Text(
                modifier = Modifier
                    .padding(top = 7.61f.bhp()),
                text = "300kcal",
                style = DungGeunMo35,
                fontSize = 35f.isp(),
                color = Color(0xFFFFA100)
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = 24.39f.bhp()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(19.86f.wp())
            ) {
                HomeNutritionCircleGraph(
                    goal = goal,
                    left = left,
                )
                Column(
                    modifier = Modifier,
                ) {
                    HomeSummaryBox(
                        title = "목표 일일 칼로리",
                        value = goal.toString() + "kcal",
                        width = 154f.wp(),
                        height = 70f.bhp()
                    )
                    HomeSummaryBox(
                        modifier = Modifier
                            .padding(top = 12f.bhp()),
                        title = "현재 체중",
                        value = current.toString() + "kg",
                        width = 154f.wp(),
                        height = 70f.bhp()
                    )
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomeMainScreenPreview() {
    val navController = rememberNavController()
    HomeMainScreen(current = 55, goal = 2300, left = 800, navController = navController)
}
