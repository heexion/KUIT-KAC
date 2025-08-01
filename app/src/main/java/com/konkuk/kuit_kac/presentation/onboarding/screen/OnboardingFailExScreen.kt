package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAppetite
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun OnboardingFailExScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNextClick: (List<String>) -> Unit = {},
    onDirectInputClick: () -> Unit = {}
) {
    val options = listOf("야식", "스트레스성\n   폭식", "식단 기록\n  귀찮음", "의지 부족")
    val selectedOptions = remember { mutableStateListOf<String>() }

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
            bubbleText = "실패한 경험이 있다면,\n그 이유가 뭐라고 생각해?",
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

            // 다음 버튼
            ConfirmButton(
                isAvailable = selectedOptions.isNotEmpty(),
                value = if (selectedOptions.isNotEmpty()) "다음 단계로" else "직접 입력하기",
                height = 65f,
                modifier = Modifier.width(364f.wp()),
                onClick = {
                    if (selectedOptions.isNotEmpty()) {
                        navController.navigate(OnboardingAppetite.route) //다음단계로
                    } else {
                        onDirectInputClick() //직접 입력하는 화면 넣어야함
                    }
                }
            )
        }
    }
}


@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isAvailable: Boolean = false,
    value: String,
    height: Float = 60f
) {

    val image = if (isAvailable) R.drawable.bg_plan_confirm_button_selected
    else R.drawable.bg_confirmbutton


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.bhp())
            .clickable { if (isAvailable) onClick() }
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview
@Composable
private fun OnboardingFailExScreenPreview () {
    val navController = rememberNavController()
    OnboardingFailExScreen(navController = navController)
}