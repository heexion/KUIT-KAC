
package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealDetailCard
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealTopBarWithSearch
import com.konkuk.kuit_kac.presentation.mealdiet.meal.foodInfoMap
import com.konkuk.kuit_kac.presentation.navigation.Route

import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealSearchItemDetailScreen(
    foodName: String,
    navController: NavHostController,
    viewModel: FoodViewModel = hiltViewModel()
) {
    val foodInfo = viewModel.food

    LaunchedEffect(foodName) {
        viewModel.loadFoodByName(foodName)
    }

    if (foodInfo == null) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFFBE8))) {

        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFBE8))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0f.hp())
                    .background(color = Color(0xFFFFF1AB))
            )
            // 공통 상단 컴포저블 사용
            MealTopBarWithSearch(
                modifier = Modifier,
                title = "식단 기록하기",
                placeholderText = foodName,
                placeholderTextColor = Color(0xFF000000),
                placeholderTextStyle = DungGeunMo20,
                showClearButton = true,
                onBackClick = { navController.popBackStack() },
                onSearchClick = { navController.navigate("meal_search") },
                onClearClick = { navController.navigate("meal_search") }
            )

            Spacer(modifier = Modifier.height(82f.bhp()))

            // 음식 상세 카드
            MealDetailCard(
                modifier = Modifier.padding(horizontal = 24f.wp()),
                image = R.drawable.ic_chickenbreast,
                foodName = foodInfo.name,
                carbohydrate = foodInfo.carb.toFloat(),
                protein = foodInfo.protein.toFloat(),
                fat = foodInfo.fat.toFloat(),
                baseCalories = foodInfo.calorie.toInt(),
                unitWeight = 150,
                isSpeechBubble = true
            )

            Spacer(modifier = Modifier.height(38f.bhp()))
// 하단 추가 버튼
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24f.wp()),
                onClick = { navController.navigate(Route.MealTime.route) },
                value = "추가하기",
                buttonHeight = 70f,
                isOrange = true
            )

        }
        }
}



@Preview(showBackground = true)
@Composable
fun MealSearchItemDetailScreenPreview() {
    val navController = rememberNavController()

    MealSearchItemDetailScreen(
        foodName = "돼지고기 김치찌개",
        navController = navController
    )
}
