package com.konkuk.kuit_kac.data.response

data class HomeSummaryResponseDto(
    val dailyKcalorieGoal: Int,
    val totalIntakeKcalorie: Int,
    val weight: Int,
    val remainingKcalorie: Int
)