package com.konkuk.kuit_kac.presentation.home.repository

import com.konkuk.kuit_kac.data.response.HomeSummaryResponseDto
import com.konkuk.kuit_kac.data.response.RoutineResponseDto
import com.konkuk.kuit_kac.data.service.HomeSummaryApiService
import okhttp3.Response
import javax.inject.Inject

interface HomeSummaryRepository {
    suspend fun getHomeSummary(userId: Int): Result<HomeSummaryResponseDto>
    suspend fun getSummary(userId: Int): retrofit2.Response<HomeSummaryResponseDto>
}

class HomeSummaryRepositoryImpl @Inject constructor(
    private val apiService: HomeSummaryApiService
) : HomeSummaryRepository {
    override suspend fun getHomeSummary(userId: Int): Result<HomeSummaryResponseDto> {
        return try {
            val response = apiService.getHomeSummary(userId)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("응답 본문이 비어있습니다"))
                }
            } else {
                Result.failure(Exception("요약 정보 불러오기 실패: 에러 코드 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSummary(userId: Int): retrofit2.Response<HomeSummaryResponseDto> {
        return apiService.getHomeSummary(userId)
    }
}
