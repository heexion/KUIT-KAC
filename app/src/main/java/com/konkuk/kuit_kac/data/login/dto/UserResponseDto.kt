package com.konkuk.kuit_kac.data.login.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val userId: Long,
    val gender: String,
    val age: Int,
    val height: Int,
    val targetWeight: Double,
    val termsAgreed: Boolean,
    val onboardingNeeded: Boolean
)