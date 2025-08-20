package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeightResponseDto(
    @SerialName("weight") val weight: Float? = 0f,
    @SerialName("createdAt") val createdAt: String? = ""
)
