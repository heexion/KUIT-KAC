package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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

    @PUT("diets/records/{dietId}")
    suspend fun changeRecord(
        @Path("dietId") dietId: Int,
        @Body request: MealRequestDto
    ): Response<Unit>
}