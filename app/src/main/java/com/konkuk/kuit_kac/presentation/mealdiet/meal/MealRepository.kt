package com.konkuk.kuit_kac.presentation.mealdiet.meal

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.service.MealService
import javax.inject.Inject

interface MealRepository {
    suspend fun createMeal(request: MealRequestDto)
}

class MealRepositoryImpl @Inject constructor(
    private val api: MealService
) : MealRepository {
    override suspend fun createMeal(request: MealRequestDto) {
        api.createMeal(request)
    }
}