package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionResponseDto(
    @SerialName("carbGoal") val carbGoal: Int? = 0,
    @SerialName("proteinGoal") val proteinGoal: Int? = 0,
    @SerialName("sugarGoal") val sugarGoal: Int? = 0,
    @SerialName("fatGoal") val fatGoal: Int? = 0,

    @SerialName("carbTaken") val carbTaken: Int? = 0,
    @SerialName("proteinTaken") val proteinTaken: Int? = 0,
    @SerialName("sugarTaken") val sugarTaken: Int? = 0,
    @SerialName("fatTaken") val fatTaken: Int? = 0,

    @SerialName("carbRatio") val carbRatio: Int? = 0,
    @SerialName("proteinRatio") val proteinRatio: Int? = 0,
    @SerialName("sugarRatio") val sugarRatio: Int? = 0,
    @SerialName("fatRatio") val fatRatio: Int? = 0
)
