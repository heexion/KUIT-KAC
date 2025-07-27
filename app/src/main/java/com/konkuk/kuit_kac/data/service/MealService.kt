package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MealService {
    @POST("diets/records")
    suspend fun createMeal(
        @Body request: MealRequestDto
    ): Response<Unit>
}