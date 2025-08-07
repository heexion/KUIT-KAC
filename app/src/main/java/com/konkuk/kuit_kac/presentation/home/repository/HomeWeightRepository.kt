package com.konkuk.kuit_kac.presentation.home.repository

import com.konkuk.kuit_kac.data.request.WeightRequestDto
import com.konkuk.kuit_kac.data.response.WeightResponseDto
import com.konkuk.kuit_kac.data.service.HomeWeightService
import retrofit2.Response
import javax.inject.Inject

interface HomeWeightRepository {
    suspend fun postWeight(request: WeightRequestDto): Response<Unit>
    suspend fun putWeight(request: WeightRequestDto): Response<Unit>
    suspend fun getWeight(userId: Int): Response<WeightResponseDto>
}

class HomeWeightRepositoryImpl @Inject constructor(
    private val api: HomeWeightService
) : HomeWeightRepository {
    override suspend fun postWeight(request: WeightRequestDto) = api.postWeight(request)
    override suspend fun putWeight(request: WeightRequestDto) = api.putWeight(request)
    override suspend fun getWeight(userId: Int) = api.getWeight(userId)
}
