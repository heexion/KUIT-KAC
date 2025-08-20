package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeSummaryResponseDto(
    @SerialName("dailyKCalorieGoal")
    val dailyKCalorieGoal: Double,
    @SerialName("totalIntakeKCalorie")
    val totalIntakeKCalorie: Double,
    @SerialName("weight")
    val weight: Double,
    @SerialName("remainingKCalorie")
    val remainingKCalorie: Double
)
