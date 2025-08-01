package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.screen.RecordButton
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingInput
import com.konkuk.kuit_kac.presentation.onboarding.component.CustomImageSlider
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15

@Composable
fun OnboardingActivityLevelScreen(modifier: Modifier = Modifier
,navController: NavHostController
                                  ) {
    var activityLevel by remember { mutableStateOf(0) }

    val activityMent = listOf(
        "매우 낮아!",
        "가벼운 활동 정도야!\n(주 1~2회 운동)",
        "보통 정도야!\n(주 3~4회 운동)",
        "활동량이 높아!\n(주 5회 이상 운동)",
        "활동량이 매우 높아!\n(매일 운동)"
    )

    Box(modifier = modifier.fillMaxSize()) {
        // 배경 + 말풍선 + 슬라이더
        OnboardingBackScreen(
            bubbleText = "평소 활동량을 알려줘!",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.4f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 말풍선 + 텍스트
            Box(
                modifier = Modifier
                    .width(233f.wp())
                    .height(87f.bhp())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_activity_bubble),
                    contentDescription = "말풍선 배경",
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = activityMent[activityLevel],
                    style = DungGeunMo15,
                    fontSize = 16f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 12f.wp())
                )
            }

            Spacer(modifier = Modifier.height(42f.bhp()))

            // 커스텀 슬라이더
            CustomImageSlider(
                currentValue = activityLevel,
                onValueChange = { activityLevel = it }
            )

            Spacer(modifier = Modifier.height(55.5f.bhp()))

            RecordButton(
                value = "다음으로",
                onClick = {
                    navController.navigate(OnboardingInput.route)
                },
                modifier = Modifier
                    .height(65f.bhp())
            )
        }
    }
}


@Preview
@Composable
private fun OnboardingActivityLevelScreenPreview() {
    val navController = rememberNavController()
    OnboardingActivityLevelScreen(navController = navController)
}