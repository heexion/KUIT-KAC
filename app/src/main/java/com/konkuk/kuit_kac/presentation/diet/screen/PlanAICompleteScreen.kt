package com.konkuk.kuit_kac.presentation.diet.screen

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.konkuk.kuit_kac.presentation.diet.component.PlanColorType
import com.konkuk.kuit_kac.presentation.diet.component.PlanConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

// 식단 추천 완료 화면
@Composable
fun PlanAICompleteScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var isDateSelected = remember { mutableStateOf(false) }

    var buttonValue = "저장하기"
    var navigateValue = "plan_result"

    if (isDateSelected.value) {
        buttonValue = "확인해볼래!"
        navigateValue = "plan_ai_detail"
    }

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
                modifier = Modifier.size(330f.wp(), 167f.bhp()),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                text = "약속 있는 다음 날까지\n식단을 짜줬어! 날짜를 누르고\n추천 식단을 확인 해봐!",
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                lineHeight = 22f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40f.bhp())
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 23.91f.wp())
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.25.dp,
                        Color.Black,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    ),
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 17.5f.wp())
                ) {
                    Text(
                        text = "날짜 선택하기",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .padding(top = 22.98f.bhp(), bottom = 10.02f.bhp())
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8f.wp())
                    ) {
                        PlanColorType(value = "AI 식단 날짜", image = R.drawable.ic_plan_circle_yellow)
                        PlanColorType(value = "외식", image = R.drawable.ic_plan_circle_blue)
                        PlanColorType(value = "술자리", image = R.drawable.ic_plan_circle_pink)
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(275.72f.bhp())
                        .padding(top = 20.98f.bhp(), start = 23.34f.wp(), end = 26.89f.wp())
                        .background(Color.LightGray)
                        .clickable {
                            isDateSelected.value = !isDateSelected.value
                        }
                )   // TODO : 달력 추후 추가
                Column {
                    PlanConfirmButton(
                        modifier = Modifier.padding(
                            top = 30.71f.bhp(),
                            start = 14.5f.wp(),
                            end = 14.5f.wp()
                        ),
                        isAvailable = true,
                        onClick = {
                            navController.navigate(navigateValue)
                        },
                        value = buttonValue,
                        height = 65f
                    )
                    Spacer(
                        modifier = Modifier.size(115f.bhp()),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanAICompleteScreenPreview() {
    val navController = rememberNavController()
    PlanAICompleteScreen(
        navController = navController
    )
}