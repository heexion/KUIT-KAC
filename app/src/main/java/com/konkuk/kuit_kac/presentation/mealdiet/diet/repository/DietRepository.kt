package com.konkuk.kuit_kac.presentation.mealdiet.diet.repository

import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.service.DietService
import javax.inject.Inject

interface DietRepository {
    suspend fun createDiet(request: DietRequestDto)
}
class DietRepositoryImpl @Inject constructor(
    private val api: DietService
):DietRepository {
    override suspend fun createDiet(request: DietRequestDto){
        api.createDiet(request)
    }
}