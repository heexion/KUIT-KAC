package com.konkuk.kuit_kac.presentation.home.repository

import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import com.konkuk.kuit_kac.data.service.CoachReportApiService
import javax.inject.Inject

interface CoachReportRepository {
    suspend fun getCoachReport(userId: Int): Result<CoachReportResponseDto>
}

class CoachReportRepositoryImpl @Inject constructor(
    private val apiService: CoachReportApiService
) : CoachReportRepository {

    override suspend fun getCoachReport(userId: Int): Result<CoachReportResponseDto> {
        return try {
            val body = apiService.getCoachReport(userId)
            Result.success(body)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
