package com.konkuk.kuit_kac.presentation.home.repository

import com.konkuk.kuit_kac.data.request.WeightRequestDto
import com.konkuk.kuit_kac.data.response.WeightResponseDto
import com.konkuk.kuit_kac.data.service.HomeWeightService
import javax.inject.Inject

interface HomeWeightRepository {
    suspend fun postWeight(request: WeightRequestDto): Result<Unit>
    suspend fun putWeight(request: WeightRequestDto): Result<Unit>
    suspend fun getWeight(userId: Int): Result<WeightResponseDto>
}


class HomeWeightRepositoryImpl @Inject constructor(
    private val api: HomeWeightService
) : HomeWeightRepository {

    override suspend fun postWeight(request: WeightRequestDto): Result<Unit> {
        return try {
            val res = api.postWeight(request)
            if (res.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("체중 등록 실패: HTTP ${res.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun putWeight(request: WeightRequestDto): Result<Unit> {
        return try {
            val res = api.putWeight(request)
            if (res.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("체중 수정 실패: HTTP ${res.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWeight(userId: Int): Result<WeightResponseDto> {
        return try {
            val res = api.getWeight(userId)
            if (res.isSuccessful) {
                val body = res.body()
                if (body != null) Result.success(body)
                else Result.failure(Exception("체중 조회 응답이 비어있습니다."))
            } else {
                Result.failure(Exception("체중 조회 실패: HTTP ${res.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
