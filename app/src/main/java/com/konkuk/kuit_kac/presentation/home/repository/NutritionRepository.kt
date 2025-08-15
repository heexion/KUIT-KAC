package com.konkuk.kuit_kac.presentation.home.repository


import com.konkuk.kuit_kac.data.response.NutritionResponseDto
import com.konkuk.kuit_kac.data.service.NutritionService
import javax.inject.Inject

interface NutritionRepository {
    suspend fun getNutrition(userId: Int): Result<NutritionResponseDto>
}

class NutritionRepositoryImpl @Inject constructor(
    private val service: NutritionService
) : NutritionRepository {
    override suspend fun getNutrition(userId: Int): Result<NutritionResponseDto> {
        return try {
            val res = service.getNutrition(userId)
            if (res.isSuccessful) {
                val body = res.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("빈 응답"))
                }
            } else {
                Result.failure(Exception("영양 정보 불러오기 실패: HTTP ${res.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
