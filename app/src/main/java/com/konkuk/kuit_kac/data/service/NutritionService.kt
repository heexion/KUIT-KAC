package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.response.NutritionResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NutritionService {
    @GET("home/nutrition")
    suspend fun getNutrition(
        @Query("userId") userId: Int
    ): Response<NutritionResponseDto>
}
