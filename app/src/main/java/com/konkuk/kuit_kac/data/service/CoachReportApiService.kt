package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoachReportApiService {

    @GET("/home/coach-report/{userId}")
    suspend fun getCoachReport(@Path("userId") userId: Int): CoachReportResponseDto
}
