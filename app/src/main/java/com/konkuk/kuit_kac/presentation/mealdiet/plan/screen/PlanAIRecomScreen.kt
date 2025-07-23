package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.diet.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.diet.component.PlanSelectButton
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanCalendar
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

// 외식 / 술자리 고려 AI 식단 추천 기능 화면
@Composable
fun PlanAIRecomScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var blueClicked = remember { mutableStateOf(false) }
    var pinkClicked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
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

        EllipseNyam(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 194.22f.bhp()),
            mascotLength = 127.45568,
            ellipseLength = 212.81445
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 57.5f.hp()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                modifier = Modifier.size(332f.wp(), 167f.bhp()),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                text = "예정된 날짜에 입력해줘!",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                lineHeight = 28f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40f.bhp())
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0x75FFF3C1), Color(0x75FFFCEE), Color(0x75FFF3C1))
                    )
                ),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 23.91f.wp())
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30f.bhp(), topEnd = 30f.bhp()))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.25.dp,
                        Color.Black,
                        RoundedCornerShape(topStart = 30f.bhp(), topEnd = 30f.bhp())
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlanCalendar(modifier = Modifier.padding(18f.wp()))
                Spacer(modifier=Modifier.size(15f.bhp()))
                Column(
                    modifier = Modifier.padding(start = 16f.wp(), end = 16f.wp(), bottom = 36.31f.bhp()),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12f.wp())
                    ) {
                        PlanSelectButton(
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                blueClicked.value = !blueClicked.value
                                pinkClicked.value = false
                            },
                            isSelected = blueClicked.value,
                            isBlue = true,
                            value = "외식",
                            height = 60f
                        )
                        PlanSelectButton(
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                blueClicked.value = false
                                pinkClicked.value = !pinkClicked.value
                            },
                            isSelected = pinkClicked.value,
                            isBlue = false,
                            value = "술자리",
                            height = 60f
                        )
                    }
                    PlanConfirmButton(
                        modifier = Modifier.padding(top = 24f.bhp()),
                        isAvailable = if (blueClicked.value || pinkClicked.value) true
                        else false,
                        onClick = {
                            if (blueClicked.value || pinkClicked.value)
                                navController.navigate("plan_ai_loading")
                        },
                        value = "다 입력했어!",
                        height = 65f
                    )
                    Spacer(
                        modifier = Modifier.size(93f.bhp()- WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PlanAIRecomScreenPreview() {
    val navController = rememberNavController()
    PlanAIRecomScreen(
        navController = navController
    )
}