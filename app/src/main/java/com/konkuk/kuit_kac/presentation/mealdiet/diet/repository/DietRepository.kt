package com.konkuk.kuit_kac.presentation.mealdiet.diet.repository

import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.service.DietService

class DietRepository(
    private val dietService: DietService
) {
    suspend fun createDiet(request: DietRequestDto) =
        kotlin.runCatching { dietService.createDiet(request) }
}