package com.konkuk.kuit_kac.presentation.home.repository


import com.konkuk.kuit_kac.data.response.NutritionResponseDto
import com.konkuk.kuit_kac.data.service.NutritionService

interface NutritionRepository {
    suspend fun getNutrition(userId: Int): Result<NutritionResponseDto>
}

class NutritionRepositoryImpl(
    private val service: NutritionService
) : NutritionRepository {
    override suspend fun getNutrition(userId: Int): Result<NutritionResponseDto> {
        return try {
            val res = service.getNutrition(userId)
            if (res.isSuccessful) {
                res.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("빈 응답"))
            } else {
                Result.failure(Exception("에러 코드: ${res.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
