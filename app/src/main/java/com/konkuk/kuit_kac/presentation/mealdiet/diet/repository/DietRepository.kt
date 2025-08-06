package com.konkuk.kuit_kac.presentation.mealdiet.diet.repository

import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.service.DietService
import okhttp3.Response
import javax.inject.Inject

interface DietRepository {
    suspend fun createDiet(request: DietRequestDto)
    suspend fun getTemplate(userId: Int): retrofit2.Response<List<MealResponseDto>>
    suspend fun changeDiet(dietId: Int, request: DietRequestDto): retrofit2.Response<Unit>
}
class DietRepositoryImpl @Inject constructor(
    private val api: DietService
):DietRepository {
    override suspend fun createDiet(request: DietRequestDto){
        api.createDiet(request)
    }

    override suspend fun getTemplate(userId: Int): retrofit2.Response<List<MealResponseDto>> {
        return api.getTemplate(userId)
    }

    override suspend fun changeDiet(dietId: Int, request: DietRequestDto): retrofit2.Response<Unit> {
        return api.changeDiet(dietId, request)
    }
}