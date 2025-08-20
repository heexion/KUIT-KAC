package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingDietSpeed
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.presentation.onboarding.OnboardingViewModel

@Composable
fun OnboardingPreferTypeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()

) {
    val options = listOf("패스트푸드", "한식", "중식", "양식", "아시안", "튀김류")
    val selectedOptions = remember { mutableStateListOf<String>() }

    val onNextClick: (List<String>) -> Unit = {
        navController.navigate(OnboardingDietSpeed.route)
    }
    val eatingOutMap = mapOf(
        "패스트푸드" to "FAST_FOOD",
        "한식"     to "KOREAN",
        "중식"     to "CHINESE",
        "양식"     to "WESTERN",
        "아시안"   to "ASIAN",
        "튀김류"    to "FRIED"
    )


    fun toggle(option: String) {
        if (selectedOptions.contains(option)) {
            selectedOptions.remove(option)
        } else {
            selectedOptions.add(option)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // 배경
        OnboardingBackScreen(
            bubbleText = "선호하는 외식 유형이 있어?",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

        // 선택 버튼 + 다음 버튼 그룹
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(),
                    end = 24f.wp(),
                    bottom = 50f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(20f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16f.wp())) {
                SelectButton(
                    value = options[0],
                    isSelected = selectedOptions.contains(options[0]),
                    buttonHeight = 70,
                    onClick = { toggle(options[0]) },
                    modifier = Modifier.width(174f.wp())
                )
                SelectButton(
                    value = options[1],
                    isSelected = selectedOptions.contains(options[1]),
                    buttonHeight = 70,
                    onClick = { toggle(options[1]) },
                    modifier = Modifier.width(174f.wp())
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16f.wp())) {
                SelectButton(
                    value = options[2],
                    isSelected = selectedOptions.contains(options[2]),
                    buttonHeight = 70,
                    onClick = { toggle(options[2]) },
                    modifier = Modifier.width(174f.wp())
                )
                SelectButton(
                    value = options[3],
                    isSelected = selectedOptions.contains(options[3]),
                    buttonHeight = 70,
                    onClick = { toggle(options[3]) },
                    modifier = Modifier.width(174f.wp())
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16f.wp())) {
                SelectButton(
                    value = options[4],
                    isSelected = selectedOptions.contains(options[4]),
                    buttonHeight = 70,
                    onClick = { toggle(options[4]) },
                    modifier = Modifier.width(174f.wp())
                )
                SelectButton(
                    value = options[5],
                    isSelected = selectedOptions.contains(options[5]),
                    buttonHeight = 70,
                    onClick = { toggle(options[5]) },
                    modifier = Modifier.width(174f.wp())
                )
            }
            // 다음 버튼
            OnboardingConfirmButton(
                isAvailable = selectedOptions.isNotEmpty(),
                value = "다음 단계로",
                height = 65f,
                modifier = Modifier.width(364f.wp()),
                onClick = {
                    if (selectedOptions.isNotEmpty()) {
                        val codes = selectedOptions
                            .mapNotNull { eatingOutMap[it] }
                            .distinct()
                            .joinToString(", ")

                        onboardingViewModel.setEatingOutType(codes)
                        onNextClick(selectedOptions.toList())
                    }
                }
            )
        }
    }
}


@Preview
@Composable
private fun OnboardingPreferTypeScreenPreview() {
    val navController = rememberNavController()
    OnboardingPreferTypeScreen(navController = navController)

}