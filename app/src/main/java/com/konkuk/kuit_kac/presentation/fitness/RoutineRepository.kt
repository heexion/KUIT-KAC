package com.konkuk.kuit_kac.presentation.fitness

import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import com.konkuk.kuit_kac.data.service.RoutineService
import javax.inject.Inject

interface RoutineRepository {
    suspend fun createRoutine(request: RoutineRequestDto)
}

class RoutineRepositoryImpl @Inject constructor(
    private val api: RoutineService
): RoutineRepository {
    override suspend fun createRoutine(request: RoutineRequestDto) {
        api.createRoutine(request)
    }
}