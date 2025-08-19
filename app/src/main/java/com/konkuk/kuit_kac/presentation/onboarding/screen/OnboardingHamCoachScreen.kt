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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingNyamCoach
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun OnboardingHamCoachScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bubbleText: String = "나부터 소개를 하자면\n나는 앞으로 너의 식습관을\n개선해주는 코치가 될 거야",
    bubbleFontSize: TextUnit = 20f.isp(),
) {
    Box(
        modifier = modifier
            .fillMaxSize()
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
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.bhp())
                .align(Alignment.TopCenter)
        )

        // 말풍선
        Box(
            modifier = Modifier
                .padding(top = 79f.bhp(), start = 24f.wp())
                .size(width = 364f.wp(), height = 206f.bhp())
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_intro_bubble),
                contentDescription = "말풍선",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = bubbleText,
                style = DungGeunMo20.copy(fontSize = bubbleFontSize),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24f.wp())
                    .offset(y = (-16f).bhp())
            )
        }
        Spacer(modifier = Modifier.height(12f.bhp()))

        // "<햄코치>" 텍스트
        Text(
            text = "〈햄코치〉",
            style = DungGeunMo24,
            color = Color(0xFF000000),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 324.9f.bhp())
        )

        // 캐릭터 이미지
//        EllipseNyam(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(top = 325f.bhp()),
//            ellipseLength = 293.6,
//            mascotLength = 171.4
//        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            HamcoachGif(
                modifier = Modifier
                    .padding(top = 340f.bhp()),
                num = 1,
                ellipseLength = 293.6,
                mascotLength = 269.0,
            )
        }



        // 그림자 이미지
        Image(
            painter = painterResource(id = R.drawable.img_hamcoach_shadow),
            contentDescription = "캐릭터 그림자",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = (325 + 171.4 * 0.5f + 170).toFloat().bhp())
                .size(width = 146f.wp(), height = 26f.bhp())
        )

        // 햄코치 설명 박스
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 649f.bhp())
                .size(width = 364f.wp(), height = 103f.bhp()),
            contentAlignment = Alignment.TopStart
        ) {
            // 체크 아이콘 + 텍스트
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 20f.wp())
                    .offset(y = (-24f).bhp())
                    .zIndex(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "체크 아이콘",
                    modifier = Modifier
                        .size(16f.wp())
                        .padding(end = 6f.wp())
                )
                Text(
                    text = "햄코치가 하는 일",
                    style = DungGeunMo17.copy(fontSize = 17f.isp()),
                    color = Color(0xFF000000)
                )
            }

            // 박스 배경
            Image(
                painter = painterResource(id = R.drawable.bg_introduce),
                contentDescription = "햄코치 설명 박스",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            // 설명 내용
            Column(
                modifier = Modifier.padding(horizontal = 20f.wp()),
                verticalArrangement = Arrangement.spacedBy(12f.bhp())
            ) {
                Spacer(modifier = Modifier.height(4f.bhp()))
                Text(
                    text = "1. 식사시간에 푸시알람으로 리마인드",
                    style = DungGeunMo15,
                    color = Color(0xFF000000)
                )
                Text(
                    text = "2. 기록하거나 계획한 식단대로 먹었다면\n    냠이를 칭찬해줘!",
                    style = DungGeunMo15,
                    color = Color(0xFF000000)
                )
            }
        }

        // 다음 버튼
        OnboardingButton(
            value = "다음으로",
            onClick = {
                navController.navigate(OnboardingNyamCoach.route)
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 772f.bhp())
                .height(65f.bhp())
        )
    }
}


@Preview
@Composable
private fun OnboardingHamCoachScreenPreview() {
    val navController = rememberNavController()
    OnboardingHamCoachScreen(navController = navController)
}