package com.konkuk.kuit_kac.presentation.home.repository

import com.konkuk.kuit_kac.data.response.HomeSummaryResponseDto
import com.konkuk.kuit_kac.data.service.HomeSummaryApiService
import javax.inject.Inject

interface HomeSummaryRepository {
    suspend fun getHomeSummary(userId: Int): Result<HomeSummaryResponseDto>
}

class HomeSummaryRepositoryImpl @Inject constructor(
    private val apiService: HomeSummaryApiService
) : HomeSummaryRepository {
    override suspend fun getHomeSummary(userId: Int): Result<HomeSummaryResponseDto> {
        return try {
            val response = apiService.getHomeSummary(userId)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("요약 정보 불러오기 실패: 에러 코드 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
