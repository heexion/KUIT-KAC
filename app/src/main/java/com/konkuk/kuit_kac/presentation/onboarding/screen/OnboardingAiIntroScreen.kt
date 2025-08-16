package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingGray

@Composable
fun OnboardingAiIntroScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // 스크롤 가능
            .noRippleClickable {
                // 다음 화면으로 이동
                navController.navigate(OnboardingGray.route)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_onboarding_ai_introduce),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingAiIntroScreen() {
    val navController = rememberNavController()
    OnboardingAiIntroScreen(navController = navController)
}
