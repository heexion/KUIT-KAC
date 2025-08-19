package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.OnboardingRequestDto
import com.konkuk.kuit_kac.data.request.PlanRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingService {
    @POST("onboarding")
    suspend fun postOnboarding(
        @Body request: OnboardingRequestDto
    ): Response<Unit>
}