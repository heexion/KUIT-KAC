package com.konkuk.kuit_kac.presentation.mealdiet.meal

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.service.MealService
import retrofit2.Response
import javax.inject.Inject

interface MealRepository {
    suspend fun createMeal(request: MealRequestDto)
    suspend fun getRecord(userId: Int): Response<List<MealResponseDto>>
}

class MealRepositoryImpl @Inject constructor(
    private val api: MealService
) : MealRepository {
    override suspend fun createMeal(request: MealRequestDto) {
        api.createMeal(request)
    }
    override suspend fun getRecord(userId: Int): Response<List<MealResponseDto>>{
        return api.getRecord(userId)
    }
}