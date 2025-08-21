package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiFoodResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("unitType")
    val unitType: String,
    @SerialName("foodType")
    val foodType: String,
    @SerialName("isProcessedFood")
    val isProcessedFood: Boolean,
    @SerialName("calorie")
    val calorie: Double,
    @SerialName("carbohydrate")
    val carbohydrate: Double,
    @SerialName("protein")
    val protein: Double,
    @SerialName("fat")
    val fat: Double,
    @SerialName("sugar")
    val sugar: Double = 0.0,
    @SerialName("score")
    val score: Int,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String
)