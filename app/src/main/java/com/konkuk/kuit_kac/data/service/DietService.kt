package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DietService {
    @POST("diets/templates")
    suspend fun createDiet(
        @Body request: DietRequestDto
    ): Response<Unit>
    @GET("diets/templates/profiles")
    suspend fun getTemplate(
        @Query("userId") userId: Int
    ): Response<List<MealResponseDto>>
    @PUT("diets/templates/{dietId}")
    suspend fun changeDiet(
        @Path("dietId") dietId: Int,
        @Body request: DietRequestDto
    ): Response<Unit>
}