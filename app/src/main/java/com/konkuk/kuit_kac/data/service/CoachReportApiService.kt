package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoachReportApiService {

    @GET("/home/coach-report")
    suspend fun getCoachReport(@Query("userId") userId: Int): CoachReportResponseDto
}
