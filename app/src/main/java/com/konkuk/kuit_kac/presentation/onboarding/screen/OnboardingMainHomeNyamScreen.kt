package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
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
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeScale
import com.konkuk.kuit_kac.presentation.onboarding.component.NyamCoach
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun OnboardingMainHomeNyamScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable {
                navController.navigate(OnboardingMainHomeScale.route)
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
            text = "냠이를 터치하면\n현재 뱃속 정보를 볼 수 있어!",
            style = DungGeunMo17,
            fontSize = 17f.isp(),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 80f.wp(), top = 114f.bhp())
        )

        // 4. 냠이 캐릭터
        NyamCoach(
            modifier = Modifier
                .width(206.44f.wp())
                .height(268.4f.bhp())
                .align(Alignment.TopStart)
                .offset(x = 148f.wp(), y = 150f.bhp()),
            alpha = 1f
        )

//        NyameeGif(
//            modifier = Modifier.offset(x = 50f.wp(), y = 35f.bhp()),
//            num = 1,
//            sizePercent = 1f
//        )

    }
}


@Preview
@Composable
private fun OnboardingMainHomeNyamScreenPreview() {
    val navController = rememberNavController()
    OnboardingMainHomeNyamScreen(navController = navController)
}