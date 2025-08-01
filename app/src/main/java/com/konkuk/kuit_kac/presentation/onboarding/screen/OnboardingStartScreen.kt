package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingDiet
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun OnboardingStartScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        OnboardingBackScreen(
            bubbleText = "", // 실제 사용 안함, 아래 커스텀으로 대체
        )

        // 커스텀 말풍선만 따로 오버레이
        Box(
            modifier = Modifier
                .padding(top = 79f.hp(), start = 24f.wp())
                .size(width = 364f.wp(), height = 206f.bhp())
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_onbubble),
                contentDescription = "말풍선",
                modifier = Modifier.fillMaxSize()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24f.wp())
                    .offset(y = (-60f).hp())
            ) {
                Text(
                    text = "반가워!",
                    style = DungGeunMo20.copy(
                        fontSize = 35f.isp(),
                        color = Color(0xFF000000)
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(11.6f.bhp()))
                Text(
                    text = "냠코치를 찾아와줘서 고마워!\n간단하게 몇 가지만 물어볼게",
                    style = DungGeunMo20.copy(
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000)
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        // 하단 노란색 버튼
        DefaultButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(),
                    end = 24f.wp(),
                    bottom = 50f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                ),
            onClick = {
                navController.navigate(OnboardingDiet.route)
            },
            value = "좋아!",
            buttonHeight = 70f,
            isOrange = false
        )
    }
}


@Preview
@Composable
private fun OnboardingStartScreenPreview() {
    val navController = rememberNavController()
    OnboardingStartScreen(navController = navController)
}