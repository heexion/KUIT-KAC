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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.onboarding.component.ModeSelectButton

@Composable
fun OnboardingDietSpeedScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigate: () -> Unit
) {
    var selectedTitle by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        // 배경
        OnboardingBackScreen(
            bubbleText = "다이어트 속도는\n어느정도가 좋아?",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

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
            ModeSelectButton(
                iconRes = R.drawable.ic_mode_nyam,
                iconSize = 51f.bhp(),
                title = "냠냠모드",
                description = "가볍고 꾸준하게! (12주)",
                isSelected = selectedTitle == "냠냠모드",
                showBadge = false,
                onClick = {
                    selectedTitle = "냠냠모드"
                    onNavigate()
                }
            )

            ModeSelectButton(
                iconRes = R.drawable.ic_mode_coach,
                iconSize = 64f.bhp(),
                title = "코치모드",
                description = "이번 기회에 확실하게! (8주)",
                isSelected = selectedTitle == "코치모드",
                showBadge = true,
                onClick = {
                    selectedTitle = "코치모드"
                    onNavigate()
                }
            )

            ModeSelectButton(
                iconRes = R.drawable.ic_mode_allin,
                iconSize = 50f.bhp(),
                title = "올인모드",
                description = "짧고 강하게! (4주)",
                isSelected = selectedTitle == "올인모드",
                showBadge = false,
                onClick = {
                    selectedTitle = "올인모드"
                    onNavigate()
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingDietSpeedScreenPreview() {

    val navController = rememberNavController()
    OnboardingDietSpeedScreen(
        navController = navController,
        onNavigate = {}
    )
}