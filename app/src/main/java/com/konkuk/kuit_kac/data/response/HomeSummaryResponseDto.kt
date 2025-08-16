package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeSummaryResponseDto(
    @SerialName("dailyKcalorieGoal")
    val dailyKcalorieGoal: Int,
    @SerialName("totalIntakeKcalorie")
    val totalIntakeKcalorie: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("remainingKcalorie")
    val remainingKcalorie: Int
)
