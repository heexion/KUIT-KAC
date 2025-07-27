package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.DietRequestDto
import okhttp3.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface DietService {
    @POST("diets/records")
    fun createDiet(
        @Body request: DietRequestDto
    )
}