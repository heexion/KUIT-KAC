package com.konkuk.kuit_kac.data.login.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequestDto(
    @SerialName("Refresh-Token")
    val refreshToken: String
)