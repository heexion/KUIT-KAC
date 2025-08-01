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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingPreferType

@Composable
fun OnboardingWeekScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 배경 및 캐릭터
        OnboardingBackScreen(
            bubbleText = "일주일에 외식 횟수는\n  얼마나 돼?",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

        // 버튼 3개
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
                value = "4번 이상",
                buttonHeight = 70f,
                isOrange = false,
                onClick = {
                    navController.navigate(OnboardingPreferType.route)
                }
            )
            DefaultButton(
                value = "2~3번",
                buttonHeight = 70f,
                isOrange = false,
                onClick = {
                    navController.navigate(OnboardingPreferType.route)
                }
            )
            DefaultButton(
                value = "1번 이하",
                buttonHeight = 70f,
                isOrange = false,
                onClick = {
                    navController.navigate(OnboardingPreferType.route)
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingWeekScreenPreview() {
    val navController = rememberNavController()
    OnboardingWeekScreen(navController = navController)
}