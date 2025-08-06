package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RoutineService {
    @POST("routines")
    suspend fun createRoutine(
        @Body request: RoutineRequestDto
    ): Response<Unit>
}