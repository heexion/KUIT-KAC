package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoachReportResponseDto(
    @SerialName("diningOutNum") val diningOutNum: Int,
    @SerialName("fastingLevel") val fastingLevel: String,
    @SerialName("drinkingLevel") val drinkingLevel: String,
    @SerialName("deliveryLevel") val deliveryLevel: String,
    @SerialName("lateNightLevel") val lateNightLevel: String
)
