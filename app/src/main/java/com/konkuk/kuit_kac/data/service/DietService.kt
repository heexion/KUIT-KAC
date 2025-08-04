package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface DietService {
    @POST("diets/templates")
    suspend fun createDiet(
        @Body request: DietRequestDto
    ): Response<Unit>

}