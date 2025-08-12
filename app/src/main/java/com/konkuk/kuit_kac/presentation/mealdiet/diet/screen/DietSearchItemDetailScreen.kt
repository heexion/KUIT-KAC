package com.konkuk.kuit_kac.presentation.mealdiet.diet.screen

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
import com.konkuk.kuit_kac.core.util.context.toDrawable
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel.DietViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealDetailCard
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealTopBarWithSearch
import com.konkuk.kuit_kac.presentation.mealdiet.meal.foodInfoMap
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.navigation.Route

import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun DietSearchItemDetailScreen(
    foodName: String,
    navController: NavHostController,
    foodViewModel: FoodViewModel = hiltViewModel(),
    dietViewModel: DietViewModel = hiltViewModel()
) {
    val foodInfo = foodViewModel.food
    val quantity = foodInfo?.let { info ->
        dietViewModel.selectedFoods.find { it.food.name == info.name }?.quantity
    } ?: 0.5f

    LaunchedEffect(foodName) {
        foodViewModel.loadFoodByName(foodName)
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
                onSearchClick = {dietViewModel.getTempSearch(foodName)
                                navController.popBackStack()},
                onClearClick = {
                    dietViewModel.clearTempSearch()
                    navController.navigate(Route.DietSearch.route)
                }
            )

            Spacer(modifier = Modifier.height(82f.bhp()))

            // 음식 상세 카드
            MealDetailCard(
                modifier = Modifier.padding(horizontal = 24f.wp()),
                image = foodInfo.foodType.toDrawable(),
                foodName = foodInfo.name,
                carbohydrate = foodInfo.carb.toFloat(),
                protein = foodInfo.protein.toFloat(),
                fat = foodInfo.fat.toFloat(),
                baseCalories = foodInfo.calorie.toInt(),
                unitWeight = 150,
                isSpeechBubble = true,
                initialQuantity = quantity,
                onQuantityChange = { newQuantity ->
                    foodInfo?.let { food ->
                        val existing =
                            dietViewModel.selectedFoods.find { it.food.name == food.name }
                        if (existing != null) {
                            dietViewModel.removeFood(existing)
                        }
                        dietViewModel.addFood(food, newQuantity)
                    }
                }
            )

            Spacer(modifier = Modifier.height(38f.bhp()))
// 하단 추가 버튼
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24f.wp()),
                onClick = {
                    foodInfo?.let { food ->
                        dietViewModel.addFood(food, quantity)
                        navController.navigate("Diet")
                    }
                },
                value = "추가하기",
                buttonHeight = 70f,
                isOrange = true
            )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DietSearchItemDetailScreenPreview() {
    val navController = rememberNavController()

    DietSearchItemDetailScreen(
        foodName = "돼지고기 김치찌개",
        navController = navController
    )
}
