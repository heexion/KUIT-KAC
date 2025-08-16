package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.Serializable

@Serializable
data class HomeSummaryResponseDto(
    val dailyKcalorieGoal: Int? = 0,
    val totalIntakeKcalorie: Int? = 0,
    val weight: Int? = 0,
    val remainingKcalorie: Int? = 0
)
