package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// 저장된 식단 계획 확인 화면
@Composable
fun PlanCheckScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "저장된 식단 계획 확인 화면"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanCheckScreenPreview() {
    PlanCheckScreen(
    )
}