package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.WeightRequestDto
import com.konkuk.kuit_kac.data.response.WeightResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HomeWeightService {
    @POST("home/weight")
    suspend fun postWeight(@Body request: WeightRequestDto): Response<Unit>

    @PUT("home/weight")
    suspend fun putWeight(@Body request: WeightRequestDto): Response<Unit>

    @GET("home/weight/{userId}")
    suspend fun getWeight(@Path("userId") userId: Int): Response<WeightResponseDto>
}
