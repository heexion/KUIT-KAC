package com.konkuk.kuit_kac.presentation.diet.screen

import com.konkuk.kuit_kac.R

data class FoodInfo(
    val image: Int,
    val carbohydrate: Float,
    val protein: Float,
    val fat: Float,
    val calories: Int,
    val unitWeight: Int
)

val foodInfoMap = mapOf(
    "마라탕" to FoodInfo(R.drawable.ic_stew_hotpot, 5.2f, 3.0f, 4.1f, 350, 500),
    "파스타" to FoodInfo(R.drawable.ic_noodles, 20.5f, 6.0f, 7.2f, 480, 300),
    "족발" to FoodInfo(R.drawable.ic_meat, 0.5f, 15.0f, 10.0f, 290, 150),
    "돼지고기 김치찌개" to FoodInfo(R.drawable.ic_stew_hotpot, 6.4f, 8.2f, 5.5f, 210, 250)
    // 계속 추가...
)
