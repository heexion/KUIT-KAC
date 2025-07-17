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

// 계획 저장 완료 화면
@Composable
fun PlanResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "계획 저장 완료 화면"
        )
    }
}

@Preview
@Composable
private fun PlanResultScreenPreview() {
    val navController = rememberNavController()
    PlanResultScreen(
        navController = navController
    )
}