package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionResponseDto(
    @SerialName("carbGoal") val carbGoal: Int,
    @SerialName("proteinGoal") val proteinGoal: Int,
    @SerialName("sugarGoal") val sugarGoal: Int,
    @SerialName("fatGoal") val fatGoal: Int,

    @SerialName("carbTaken") val carbTaken: Int,
    @SerialName("proteinTaken") val proteinTaken: Int,
    @SerialName("sugarTaken") val sugarTaken: Int,
    @SerialName("fatTaken") val fatTaken: Int,

    @SerialName("carbRatio") val carbRatio: Int,
    @SerialName("proteinRatio") val proteinRatio: Int,
    @SerialName("sugarRatio") val sugarRatio: Int,
    @SerialName("fatRatio") val fatRatio: Int
)