package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

// 외식 / 술자리 고려 AI 식단 추천 기능 화면
@Composable
fun PlanAIRecomScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "외식 / 술자리 고려 AI 식단 추천 기능 화면"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanAIRecomScreenPreview() {
    PlanAIRecomScreen(
    )
}