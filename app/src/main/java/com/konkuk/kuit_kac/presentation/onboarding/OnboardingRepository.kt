package com.konkuk.kuit_kac.presentation.onboarding

import com.konkuk.kuit_kac.data.request.OnboardingRequestDto
import com.konkuk.kuit_kac.data.response.OnboardingResponseDto
import com.konkuk.kuit_kac.data.service.OnboardingService
import retrofit2.Response
import javax.inject.Inject

interface OnboardingRepository {
    suspend fun postOnboarding(request: OnboardingRequestDto): OnboardingResponseDto
}

class OnboardingRepositoryImpl @Inject constructor(
    private val api: OnboardingService
): OnboardingRepository {
    override suspend fun postOnboarding(request: OnboardingRequestDto): OnboardingResponseDto {
        return api.postOnboarding(request)
    }
}