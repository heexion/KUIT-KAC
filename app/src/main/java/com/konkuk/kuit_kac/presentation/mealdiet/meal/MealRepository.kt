package com.konkuk.kuit_kac.presentation.mealdiet.meal

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.service.MealService

class MealRepository(
    private val mealService: MealService
) {
    suspend fun createMeal(request: MealRequestDto) =
        kotlin.runCatching { mealService.createMeal(request) }
}