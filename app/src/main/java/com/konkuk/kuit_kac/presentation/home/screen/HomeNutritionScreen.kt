package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeSingleNutritionBar
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.NyameeGif
import com.konkuk.kuit_kac.presentation.home.viewmodel.NutritionViewModel
import kotlin.random.Random

@Composable
fun HomeNutritionScreen(modifier: Modifier = Modifier) {

    val viewModel: NutritionViewModel = hiltViewModel()
    val nutrition = viewModel.nutrition.value
    val error = viewModel.error.value

    LaunchedEffect(Unit) {
        viewModel.loadNutrition()
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        val randNum = Random.nextInt(3) + 1

        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier.offset(y = 400.73f.hp())
        )

        NyameeGif(
            modifier = Modifier.offset(x = 10f.wp(), y = 50f.bhp()),
            num = randNum
        )

        Column(
            modifier = Modifier
                .padding(top = 30f.hp())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .size(248.0013f.wp(), 103.00002f.bhp()),
                    painter = painterResource(R.drawable.img_homegrpahscreen_analysistextballoon),
                    contentDescription = "현재까지 내 뱃속 정보야"
                )
                Spacer(modifier = Modifier.size(187.67596f.wp(), 244f.bhp()))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 103f.bhp())
                    .clip(RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.dp,
                        color = Color(0xFF000000),
                        RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp())
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 29.87f.bhp(), start = 40f.wp(), end = 40f.wp()),
                    verticalArrangement = Arrangement.spacedBy(16f.bhp())
                ) {
                    when {
                        nutrition != null -> {
                            HomeSingleNutritionBar(
                                type = "탄수화물",
                                base = nutrition.carbGoal ?: 0,
                                quantity = nutrition.carbTaken ?: 0
                            )
                            HomeSingleNutritionBar(
                                type = "단백질",
                                base = nutrition.proteinGoal ?: 0,
                                quantity = nutrition.proteinTaken ?: 0
                            )
                            HomeSingleNutritionBar(
                                type = "당류",
                                base = nutrition.sugarGoal ?: 0,
                                quantity = nutrition.sugarTaken ?: 0
                            )
                            HomeSingleNutritionBar(
                                type = "지방",
                                base = nutrition.fatGoal ?: 0,
                                quantity = nutrition.fatTaken ?: 0
                            )
                        }
                        error != null -> {
                            Text(
                                text = error,
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        else -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeNutritonScreenPreview() {
    HomeNutritionScreen()
}