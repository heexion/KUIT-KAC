package com.konkuk.kuit_kac.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingResponseDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("bmr")
    val bmr: Int,
    @SerialName("dailyDeficit")
    val dailyDeficit: Int,
    @SerialName("access")
    val access: String,
    @SerialName("refresh")
    val refresh: String
)