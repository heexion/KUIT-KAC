package com.konkuk.kuit_kac.data.login.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDto(
    val accessToken: String?,
    val refreshToken: String?
)