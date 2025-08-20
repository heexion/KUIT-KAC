package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.core.util.context.*
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun PlanAILoadingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mealViewModel: MealViewModel = hiltViewModel()
) {
    val pipelineState = mealViewModel.aiPipelineState.value
    val isError = pipelineState is MealViewModel.AiPipelineState.Error
    LaunchedEffect(pipelineState) {
        when (pipelineState) {
            is MealViewModel.AiPipelineState.Success -> {
                navController.navigate("PlanCheckGraph") {
                    popUpTo("plan_ai_loading") { inclusive = true }
                }
            }
            else -> Unit
        }
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            HamcoachGif(
                modifier = Modifier.offset(y = 212.5f.hp()),
                num = 4,
                ellipseLength = 263.0,
                mascotLength = 225.0,
            )
        }

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

    if (isError) {
        Dialog(
            onDismissRequest = { /* block outside dismiss; force a choice */ },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .size(364f.wp(), 202f.bhp())
                    .clip(RoundedCornerShape(20f.bhp()))
                    .background(Color(0xFFFFF3C1))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(30f.bhp()))
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 38f.bhp()),
                    text = "식단 추천 중 오류가 발생했어!\n다시 해볼래?",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26f.bhp()),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // 재시도
                    Box(
                        modifier = Modifier
                            .size(126f.wp(), 56f.bhp())
                            .clip(RoundedCornerShape(16f.bhp()))
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(30f.bhp()))
                            .clickable {
                                mealViewModel.runAiPipeline()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "재시도",
                            style = DungGeunMo17,
                            fontSize = 17f.isp()
                        )
                    }

                    // 그만두기
                    Box(
                        modifier = Modifier
                            .size(126f.wp(), 56f.bhp())
                            .clip(RoundedCornerShape(16f.bhp()))
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(30f.bhp()))
                            .clickable {
                                navController.navigate(Route.Home.route) {
                                    popUpTo("plan_ai_loading") { inclusive = true }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "그만두기",
                            style = DungGeunMo17,
                            fontSize = 17f.isp()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanAILoadingScreenPreview() {
    val navController = rememberNavController()
    PlanAILoadingScreen(navController = navController)
}
