package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.response.HomeSummaryResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeSummaryApiService {
    @GET("home/summary")
    suspend fun getHomeSummary(
        @Query("userId") userId: Int
    ): Response<HomeSummaryResponseDto>
}
