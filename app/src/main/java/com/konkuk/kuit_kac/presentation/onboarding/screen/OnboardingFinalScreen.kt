package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
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
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.navigation.Route.Home
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingStart

@Composable
fun OnboardingFinalScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onFinish: () -> Unit={}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // 스크롤 가능
            .noRippleClickable {
                // 다음 화면으로 이동
                onFinish()
                navController.navigate(Home.route) {
                    popUpTo(OnboardingStart.route) { inclusive = true }
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_onboarding_final3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingFinalScreen() {
    val navController = rememberNavController()
    OnboardingFinalScreen(navController = navController)
}
