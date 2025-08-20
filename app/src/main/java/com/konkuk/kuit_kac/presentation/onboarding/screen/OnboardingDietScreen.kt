package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAppetite
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingFailEx
import com.konkuk.kuit_kac.presentation.onboarding.OnboardingViewModel

@Composable
fun OnboardingDietScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 배경 및 캐릭터
        OnboardingBackScreen(
            bubbleText = "다이어트는\n처음이야?",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

        // 버튼 2개
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(),
                    end = 24f.wp(),
                    bottom = 25f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(20f.bhp())
        ) {
            DefaultButton(
                value = "응, 처음이야!",
                buttonHeight = 70f,
                isOrange = false,
                onClick = {
                    onboardingViewModel.setHasDietExperience(false)
                    navController.navigate(OnboardingAppetite.route)
                }
            )
            DefaultButton(
                value = "아니, 해봤어!",
                buttonHeight = 70f,
                isOrange = false,
                onClick = {
                    onboardingViewModel.setHasDietExperience(true)
                    navController.navigate(OnboardingFailEx.route)
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingDietScreenPreview() {
    val navController = rememberNavController()
    OnboardingDietScreen(navController = navController)

}