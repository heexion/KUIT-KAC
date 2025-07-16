package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//직접 식단짜기 화면

@Composable
fun PlanInPersonScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "직접 식단짜기 화면"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanInPersonScreenPreview() {
    PlanInPersonScreen(
    )
}