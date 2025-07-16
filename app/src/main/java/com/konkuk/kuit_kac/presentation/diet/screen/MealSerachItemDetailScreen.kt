package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.MealDetailCard
import com.konkuk.kuit_kac.component.MealTopBarWithSearch
import com.konkuk.kuit_kac.presentation.diet.foodInfoMap
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealSearchItemDetailScreen(
    foodName: String,
    navController: NavHostController
) {
    val foodInfo = foodInfoMap[foodName]

    if (foodInfo == null) {
        // 데이터가 없으면 바로 뒤로 이동
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFBE8))
        ) {
            // 공통 상단 컴포저블 사용
            MealTopBarWithSearch(
                title = "식단 기록하기",
                placeholderText = foodName,
                placeholderTextColor = Color.Black,
                placeholderTextStyle = DungGeunMo20,
                showClearButton = true,
                onBackClick = { navController.popBackStack() },
                onSearchClick = { navController.navigate("meal_search") },
                onClearClick = { navController.navigate("meal_search") }
            )

            Spacer(modifier = Modifier.height(82.dp))

            // 음식 상세 카드
            MealDetailCard(
                modifier = Modifier.padding(horizontal = 24.dp),
                image = foodInfo.image,
                foodName = foodName,
                carbohydrate = foodInfo.carbohydrate,
                protein = foodInfo.protein,
                fat = foodInfo.fat,
                baseCalories = foodInfo.calories,
                unitWeight = foodInfo.unitWeight,
                isSpeechBubble = true
            )

            Spacer(modifier = Modifier.height(12.dp))
// 하단 추가 버튼
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                onClick = { /* TODO: 저장 처리 */ },
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
