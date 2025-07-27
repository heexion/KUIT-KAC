package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.MealRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MealService {
    @POST("diets/records")
    fun createMeal(
        @Body request: MealRequestDto
    )
}