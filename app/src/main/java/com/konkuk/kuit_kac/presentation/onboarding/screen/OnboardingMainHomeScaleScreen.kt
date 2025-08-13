package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingFloatingButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun OnboardingMainHomeScaleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(OnboardingFloatingButton.route)
            }
    ) {
        // 1. 배경 이미지
        Image(
            painter = painterResource(id = R.drawable.bg_mainhome),
            contentDescription = "메인 홈 배경",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. 어두운 오버레이
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000))
        )

        // 3. 말풍선 텍스트 (왼쪽 위 정렬)
        Text(
            text = "체중계를 터치하면\n체중을 기록할 수 있어!",
            style = DungGeunMo17,
            fontSize = 17f.isp(),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 100f.wp(), top = 259f.bhp())
        )

        // 4. 체중계 이미지
        Image(
            painter = painterResource(id = R.drawable.ic_scale),
            contentDescription = "체중계",
            modifier = Modifier
                .width(108.1f.wp())
                .height(60.95f.bhp())
                .align(Alignment.TopStart)
                .offset(x = 80f.wp(), y = 350f.bhp())
        )
    }
}


@Preview
@Composable
private fun OnboardingMainHomeScaleScreenPreview() {
    val navController = rememberNavController()
    OnboardingMainHomeScaleScreen(navController = navController)
}