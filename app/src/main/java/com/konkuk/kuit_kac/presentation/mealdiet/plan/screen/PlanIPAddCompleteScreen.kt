package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanDietCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

// 추천된 식단 화면
@Composable
fun PlanIPAddCompleteScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentCal: Float = 1700f,
    maxCal: Float = 2500f,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20f.hp())
                    .align(Alignment.TopCenter)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 112.12f.hp()),
                    num = 1,
                    ellipseLength = 145.62891,
                    mascotLength = 110.0,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 59f.hp()), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    modifier = Modifier.size(332f.wp(), 103f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = "식단 계획이 다 입력됐어!",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 22f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24f.bhp())
                )
            }

//            EllipseNyam(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 112.12f.hp()),
//                mascotLength = 87.70016,
//                ellipseLength = 145.62891
//            )


            Column(
                modifier = Modifier
                    .padding(top = 247.59f.hp(), start = 24f.wp(), end = 24f.wp()),
            ) {
                Row(
                    Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF000000),
                            shape = RoundedCornerShape(size = 42f.bhp())
                        )
                        .width(363f.wp())
                        .height(37f.bhp())
                        .background(
                            color = Color(0xFFFFF1AB), shape = RoundedCornerShape(size = 42f.bhp())
                        )
                ) {
                    //Todo: 달력 구현 후 추가
                }
                Spacer(modifier = Modifier.size(22.5f.bhp()))
                PlanDietCard(
                    "아침", listOf("아침식단1"),
                    onClick = { navController.navigate(Route.DietPatch.route) })
                Spacer(modifier = Modifier.size(20f.bhp()))
                PlanDietCard(
                    "점심",
                    listOf("점심식단1"),
                    onClick = { navController.navigate(Route.DietPatch.route) })
                Spacer(modifier = Modifier.size(20f.bhp()))
                PlanDietCard(
                    "저녁",
                    listOf("저녁식단1"),
                    onClick = { navController.navigate(Route.DietPatch.route) })
                Spacer(modifier = Modifier.size(24f.bhp()))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(43f.bhp())
                ) {
                    Text(
                        modifier = Modifier.padding(top = 11f.bhp()),
                        text = "현재 식단의 칼로리는",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000)
                    )
                    Row(
                        modifier = Modifier.align(Alignment.TopEnd),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(end = 7.06f.wp())
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                                modifier = Modifier.size(85f.wp(), 40f.bhp()),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                            Text(
                                text = "현재 칼로리",
                                style = DungGeunMo12,
                                fontSize = 12f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 10f.bhp())
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                                modifier = Modifier.size(85f.wp(), 40f.bhp()),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                            Text(
                                text = "최대 칼로리",
                                style = DungGeunMo12,
                                fontSize = 12f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 10f.bhp())
                            )
                        }
                    }

                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(53.6f.bhp())
                        .clip(RoundedCornerShape(26.8f.bhp()))
                        .background(Color.Black)
                        .border(
                            1.dp, Color(0xFF000000), shape = RoundedCornerShape(26.8f.bhp())
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(53.6f.bhp())
                            .weight(currentCal)
                            .background(color = Color(0xFFFEB952))
                            .border(1.dp, Color(0xFF000000)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 23.2f.wp()),
                            text = "${currentCal.toInt()}kcal",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF000000)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(53.6f.bhp())
                            .weight(maxCal - currentCal)
                            .background(color = Color(0xFFD2D2D2)),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156f.bhp())
                        .padding(
                            top = 12f.bhp(), start = 16f.wp(),
                            end = 14.5f.bhp()
                        )
                ) {
                    EllipseNyam(
                        ellipseLength = 153.68867, mascotLength = 93.27386
                    )

//                    HamcoachGif(
//                        modifier = Modifier.offset(),
//                        num = 1,
//                        ellipseLength = 153.68,
//                        mascotLength = 120.0,
//                    )

                    Box(
                        modifier = Modifier
                            .width(185.85812f.wp())
                            .height(87.78002f.bhp())
                            .offset(y = 9.67f.bhp(), x = 146.38f.wp()),
                    ) {
                        Image(
                            modifier = Modifier
                                .width(185.85812f.wp())
                                .height(87.78002f.bhp()),
                            painter = painterResource(R.drawable.img_plan_textballon),
                            contentDescription = "text balloon"
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = -7f.bhp()),
                            text = "이날 완전 클린식단인데?\n좋아좋아!",
                            lineHeight = 18f.isp(),
                            style = DungGeunMo12,
                            fontSize = 12f.isp(),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                DefaultButton(
                    onClick = {
                        navController.navigate("plan_result")
                    }, value = "저장하기", buttonHeight = 65f, isOrange = true
                )

                Spacer(modifier = Modifier.size(170.2f.dp))
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanIPAddCompleteScreenPreview() {
    val navController = rememberNavController()
    PlanIPAddCompleteScreen(
        navController = navController
    )
}