package com.konkuk.kuit_kac.presentation.mealdiet.meal

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.request.PlanRequestDto
import com.konkuk.kuit_kac.data.request.SimpleRequestDto
import com.konkuk.kuit_kac.data.request.SnackRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.service.MealService
import retrofit2.Response
import javax.inject.Inject

interface MealRepository {
    suspend fun createMeal(request: MealRequestDto)
    suspend fun getRecord(userId: Int): Response<List<MealResponseDto>>
    suspend fun changeRecord(dietId: Int, request: MealRequestDto): Response<Unit>
    suspend fun createSimple(request: SimpleRequestDto): Response<Unit>
    suspend fun deleteMeal(dietId: Int)
    suspend fun createSnack(request: SnackRequestDto): Response<Unit>
    suspend fun changeSnack(dietId: Int,request: SnackRequestDto): Response<Unit>
    suspend fun createPlan(request: PlanRequestDto): Response<Unit>
    suspend fun changePlan(dietId: Int, request: PlanRequestDto): Response<Unit>
    suspend fun getPlan(userId: Int): Response<List<MealResponseDto>>
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

    override suspend fun changeRecord(dietId: Int, request: MealRequestDto): Response<Unit> {
        return api.changeRecord(dietId, request)
    }

    override suspend fun createSimple(request: SimpleRequestDto): Response<Unit> {
        return api.createSimple(request)
    }

    override suspend fun deleteMeal(dietId: Int) {
        return api.deleteMeal(dietId)
    }

    override suspend fun createSnack(request: SnackRequestDto): Response<Unit> {
        return api.createSnack(request)
    }

    override suspend fun changeSnack(dietId: Int,request: SnackRequestDto): Response<Unit> {
        return api.changeSnack(dietId, request)
    }

    override suspend fun createPlan(request: PlanRequestDto): Response<Unit> {
        return api.createPlan(request)
    }

    override suspend fun changePlan(dietId: Int, request: PlanRequestDto): Response<Unit> {
        return api.changePlan(dietId, request)
    }

    override suspend fun getPlan(userId: Int): Response<List<MealResponseDto>> {
        return api.getPlan(userId)
    }
}