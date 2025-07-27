package com.konkuk.kuit_kac.presentation.mealdiet.meal

import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.MealCardData

data class MealState(
    val mealType: String,
    val mealCardData: MealCardData? = null,
    val isFasting: Boolean = false
)
