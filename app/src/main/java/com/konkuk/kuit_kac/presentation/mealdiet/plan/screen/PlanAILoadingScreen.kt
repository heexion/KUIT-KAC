package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import kotlinx.coroutines.delay

// 입력하기 후 분석 화면
@Composable
fun PlanAILoadingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var isCompleted: Boolean

    LaunchedEffect(Unit) {
        delay(3000L) // 임시로 3초 대기 후 넘어가도록 함. TODO: 서버에서 분석 완료되면 넘어가도록 해야 함
        isCompleted = true
        if (isCompleted)
            navController.navigate("plan_ai_complete")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.hp())
                .align(Alignment.TopCenter)
        )

        EllipseNyam(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 212.5f.hp()),
            mascotLength = 158.55273,
            ellipseLength = 263.57922,
            isMascotAngry = true
        )

        Text(
            text = "햄코치가 식단 분석 중...",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 454.76f.hp()),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanAILoadingScreenPreview() {
    val navController = rememberNavController()
    PlanAILoadingScreen(
        navController = navController
    )
}