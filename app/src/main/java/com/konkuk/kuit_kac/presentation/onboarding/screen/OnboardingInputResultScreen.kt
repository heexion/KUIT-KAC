package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.screen.RecordButton
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingIntroduce
import com.konkuk.kuit_kac.presentation.onboarding.component.NyamCoach
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun OnboardingInputResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    val bubbleText = "다음과 같은\n결과가 나왔어!"
    val bottomBubbleText = "평균보다 기초 대사량이 좀 높은 편이야"
    val shadow = Color(0xFFDDD7BC)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3C1),
                        Color(0xFFFFFCEE),
                        Color(0xFFFFF3C1)
                    )
                )
            )
    ) {
        // 상단 타이틀
        Box(modifier = Modifier.fillMaxWidth()) {
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
        }

        Spacer(modifier = Modifier.height(18.9f.hp()))

        // 상단 말풍선
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.4f.wp()),
            verticalAlignment = Alignment.Top
        ) {
            EllipseNyam(
                ellipseLength = 191.2,
                mascotLength = 114.5
            )

            Box(
                modifier = Modifier
                    .size(width = 228.6f.wp(), height = 110.2f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_onboarding_bubble),
                    contentDescription = "말풍선",
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = bubbleText,
                    style = DungGeunMo17.copy(fontSize = 17f.isp()),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12f.wp())
                        .offset(y = (-12f).bhp())
                )
            }
        }

        // 냠코치 + 그림자
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260f.bhp()),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(shadow),
                modifier = Modifier
                    .alpha(0.55f)
                    .size(width = 167f.wp(), height = 47f.bhp())
                    .align(Alignment.BottomCenter)
                    .offset(y = 8f.bhp())
            )

            NyamCoach(
                modifier = Modifier
                    .width(247f.wp())
                    .height(305f.bhp())
                    .align(Alignment.Center)
            )
        }

        // 하단 말풍선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 22.5f.wp()),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .size(width = 202.473f.wp(), height = 92.017f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = bottomBubbleText,
                    style = DungGeunMo17.copy(fontSize = 17f.isp()),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24f.wp())
                )
            }
        }

        Spacer(modifier = Modifier.height(8f.bhp()))

        // 기초대사량 카드
        InputResultCard(
            label = "현재 기초 대사량",
            value = "1800 kcal",
            modifier = Modifier.padding(horizontal = 24f.wp())
        )

        Spacer(modifier = Modifier.height(20f.bhp()))

        // 감량 칼로리 카드
        InputResultCard(
            label = "일일 감량 칼로리",
            value = "200 kcal",
            modifier = Modifier.padding(horizontal = 24f.wp())
        )

        Spacer(modifier = Modifier.height(32f.bhp()))

        RecordButton(
            value = "오~ 그렇구나!",
            onClick = {
                navController.navigate(OnboardingIntroduce.route)
            },
            modifier = Modifier
                .height(65f.bhp())
        )
    }
}

@Composable
fun InputResultCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(364f.wp())
            .height(61f.bhp())
    ) {
        // 전체 배경
        Image(
            painter = painterResource(id = R.drawable.bg_input_result),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        // 텍스트 및 값 정렬
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16f.wp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 왼쪽 텍스트
            Text(
                text = label,
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFF000000)
            )

            // 오른쪽 값 박스
            Box(
                modifier = Modifier
                    .size(width = 134f.wp(), height = 47f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_input_result_in),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = value,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }
        }
    }
}


@Preview
@Composable
private fun OnboardingInputResultScreenPreview() {
    val navController = rememberNavController()
    OnboardingInputResultScreen(navController = navController)

}