package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingHamCoach
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingButton

@Composable
fun OnboardingIntroduceScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Box(modifier = modifier.fillMaxSize()) {

            OnboardingBackScreen(
                bubbleText = "자, 이제 우리를\n소개할 시간이야",
                bubbleFontSize = 24f.isp()

            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 25f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                            .calculateBottomPadding()
                    )
            ) {
                // 하단 버튼
                OnboardingButton(
                    value = "너희 정체가 궁금해!",
                    onClick = {  navController.navigate(OnboardingHamCoach.route) },
                    modifier = Modifier
                        .width((364f.wp()))
                        .height(65f.bhp())


                )
            }
        }
    }
}


@Preview
@Composable
private fun OnboardingIntroduceScreenPreview() {
    val navController = rememberNavController()
    OnboardingIntroduceScreen(navController = navController)
}