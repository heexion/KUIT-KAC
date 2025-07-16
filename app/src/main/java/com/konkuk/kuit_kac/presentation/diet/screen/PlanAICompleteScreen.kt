package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// 식단 추천 완료 화면
@Composable
fun PlanAICompleteScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "식단 추천 완료 화면"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanAICompleteScreenPreview() {
    val navController = rememberNavController()
    PlanAICompleteScreen(
        navController = navController
    )
}