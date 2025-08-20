package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeHam
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingDeliveryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

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

        // 타이틀
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20f.bhp())
        )

        // 배경 텍스트 박스
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboarding_text),
                contentDescription = "텍스트 배경",
                modifier = Modifier
                    .width(429.8f.wp())
                    .height(144.8f.bhp())
            )
            Text(
                text = "배달 습관?\n냠코치가 바로 잡아줄게!",
                style = DungGeunMo24.copy(fontSize = 24f.isp()),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16f.wp())
            )
        }

        // 페이지마다 다른 설명 텍스트
        val detailText = when (pagerState.currentPage) {
            0 -> "식단이 비어 있을 땐, 배달 주문 알림을\n보고 내가 먼저 물어볼게!"
            else -> "오늘 식단 기록 끝났는데 또 배달?\n냠코치가 관리해줄게!"
        }
        Text(
            text = detailText,
            style = DungGeunMo17.copy(fontSize = 17f.isp()),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = (80f + 144.8f + 8f).bhp())
        )


        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(333f.wp())
                .height(604f.bhp())
        ) { page ->
            val imageRes = if (page == 0) R.drawable.img_onboarding_phone1 else R.drawable.img_onboarding_phone2
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "배달 알림 예시",
                modifier = Modifier.fillMaxSize()
            )
        }

        // RecordButton
        OnboardingButton(
            value = "잘 알겠어!",
            onClick = {
                navController.navigate(OnboardingMainHomeHam.route)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-80f).bhp())
                .height(65f.bhp())
        )

        // 인디케이터
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40f.bhp())
                .alpha(0f),
            horizontalArrangement = Arrangement.spacedBy(8f.wp())
        ) {
            repeat(2) { index ->
                Box(
                    modifier = Modifier
                        .size(8f.wp())
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) Color.Black
                            else Color(0x33000000)
                        )
                )
            }
        }
    }
}


@Preview
@Composable
private fun OnboardingDeliveryScreenPreview() {
    val navController = rememberNavController()
    OnboardingDeliveryScreen(navController = navController)

}