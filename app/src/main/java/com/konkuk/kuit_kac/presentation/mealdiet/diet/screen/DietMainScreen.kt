package com.konkuk.kuit_kac.presentation.mealdiet.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow

@Composable
fun DietMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(165f.bhp())
                .background(color = deepYellow)
                .border(1.dp, Color(0xFF000000))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16f.hp()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(28.8584f.wp(),28.8584f.bhp()),
                    painter = painterResource(R.drawable.ic_alcohol),
                    contentDescription = "utensils"
                )
                Text(
                    text = "식단",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 17f.bhp(),
                        start = 24f.wp(), end = 24f.wp()),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp())
                        .clickable {
                            navController.navigate(Route.Meal.route)
                        },
                    value = "식단기록"
                )
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp()),
                    value = "나만의 식단"
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush =
                    Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFFFF),Color(0xFFFFF4C1)),
                        radius = 2000f
                    )
                ),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 28f.bhp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(364f.wp(), 49f.bhp())
                        .clip(RoundedCornerShape(24f.bhp()))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(24f.bhp()))
                        .clickable(
                            onClick = {
                                navController.navigate(Route.DietCreate.route)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_diet_cross),
                            contentDescription = "plus",
                            modifier = Modifier
                                .size(14.20361f.wp(),14.20361f.bhp())
                        )
                        Text(
                            text = "나만의 식단 생성하기",
                            style = DungGeunMo17,
                            fontSize = 17f.isp(),
                            color = Color(0xFF000000)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(364f.wp(),458f.bhp())
                    .offset(y = 105f.bhp(), x = 24f.wp())
                    .clip(RoundedCornerShape(20f.wp()))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                            radius = 1200f
                        )
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.img_diet_maintextballoon),
                    contentDescription = "text balloon",
                    modifier = Modifier
                        .size(219f.wp(),83f.bhp())
                        .offset(x = 73f.wp(), y = 101f.bhp())
                )
                Box(
                    modifier = Modifier
                        .size(114f.wp(),44f.bhp())
                        .offset(135.27f.wp(),113f.bhp()),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "현재식단이 비어있어요!",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }

                EllipseNyam(
                    modifier = Modifier
                        .offset(x = 87f.wp(), y = 174f.bhp())
                        .clickable(
                            onClick = {
                                navController.navigate(Route.DietExist.route)
                            }
                        ),
                    ellipseLength = 182.0,
                    mascotLength = 109.0
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DietMainScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    DietMainScreen(navController = navController)
}