package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingEoDrink

@Composable
fun OnboardingYellowScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    // 처음 진입 시 스크롤 위치 조정
    LaunchedEffect(Unit) {
        scrollState.scrollTo(100)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // 스크롤 가능
            .noRippleClickable {
                // 다음 화면으로 이동
                navController.navigate(OnboardingEoDrink.route)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_onboarding_yellow3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingYellowScreen() {
    val navController = rememberNavController()
    OnboardingYellowScreen(navController = navController)
}
