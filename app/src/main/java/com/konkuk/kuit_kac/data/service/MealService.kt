package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import okhttp3.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealService {
    @POST("diets/records")
    suspend fun createMeal(
        @Body request: MealRequestDto
    ): Response<Unit>

    @GET("diets/records/profiles")
    suspend fun getRecord(
        @Query("userId") userId: Int
    ): Response<List<MealResponseDto>>
}