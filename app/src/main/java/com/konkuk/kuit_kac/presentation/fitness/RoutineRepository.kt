package com.konkuk.kuit_kac.presentation.fitness

import com.konkuk.kuit_kac.data.request.RecordRequestDto
import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import com.konkuk.kuit_kac.data.response.RecordResponseDto
import com.konkuk.kuit_kac.data.response.RoutineResponseDto
import com.konkuk.kuit_kac.data.service.RoutineService
import retrofit2.Response
import javax.inject.Inject

interface RoutineRepository {
    suspend fun createRoutine(request: RoutineRequestDto): Response<Unit>
    suspend fun createRoutineRecord(request: RecordRequestDto): Response<Unit>
    suspend fun changeRoutine(dietId: Int, request: RoutineRequestDto): Response<Unit>
    suspend fun getRoutineRecord(userId: Int): Response<List<RecordResponseDto>>
    suspend fun getRoutineTemplate(userId: Int): Response<List<RoutineResponseDto>>
    suspend fun deleteRoutine(dietId: Int)
}

class RoutineRepositoryImpl @Inject constructor(
    private val api: RoutineService
): RoutineRepository {
    override suspend fun createRoutine(request: RoutineRequestDto): Response<Unit> {
        return api.createRoutine(request)
    }

    override suspend fun createRoutineRecord(request: RecordRequestDto): Response<Unit> {
        return api.createRoutineRecord(request)
    }

    override suspend fun changeRoutine(dietId: Int, request: RoutineRequestDto): Response<Unit> {
        return api.changeRoutine(dietId,request)
    }

    override suspend fun getRoutineRecord(userId: Int): Response<List<RecordResponseDto>> {
        return api.getRoutineRecord(userId)
    }

    override suspend fun getRoutineTemplate(userId: Int): Response<List<RoutineResponseDto>> {
        return api.getRoutineTemplate(userId)
    }
    override suspend fun deleteRoutine(dietId: Int) {
        return api.deleteRoutine(dietId)
    }
}