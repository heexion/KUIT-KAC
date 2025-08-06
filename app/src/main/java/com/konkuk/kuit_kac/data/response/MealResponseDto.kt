package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("dietType")
    val dietType: String,
    @SerialName("dietEntryType")
    val dietEntryType: String,
    @SerialName("foodStatusType")
    val foodStatusType: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("totalKcal")
    val totalKcal: Double,
    @SerialName("dietFoods")
    val dietFoods: List<FoodListResponseDto>
)

@Serializable
data class FoodListResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("dietTime")
    val dietTime: String,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("food")
    val food: FoodResponseDto
)

@Serializable
data class FoodResponseDto(
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
    @SerialName("score")
    val score: Int,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String
)