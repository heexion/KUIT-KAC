package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeNyam
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun OnboardingMainHomeHamScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable {
                navController.navigate(OnboardingMainHomeNyam.route)
            }
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(id = R.drawable.bg_mainhome),
            contentDescription = "메인 홈 배경",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 어두운 오버레이
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000))
        )

        HamcoachGif(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20f.wp(), top = 80f.bhp()),
            num = 1,
            ellipseLength = 147.6,
            mascotLength = 120.0,
        )

        // 햄코치 + 말풍선 그룹
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 29f.wp(), top = 115f.bhp())
        ) {
//            EllipseNyam(
//                ellipseLength = 147.6,
//                mascotLength = 88.4
//            )

            Text(
                text = "햄코치를 터치하면\n관찰일지를 확인할 수 있어!",
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 120f.wp(), y = (-55f).bhp())
                    .padding(horizontal = 12f.wp(), vertical = 8f.bhp())
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingMainHomeHamScreenPreview() {
    val navController = rememberNavController()
    OnboardingMainHomeHamScreen(navController = navController)
}
