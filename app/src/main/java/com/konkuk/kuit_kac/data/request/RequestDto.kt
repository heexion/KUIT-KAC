package com.konkuk.kuit_kac.data.request

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealRequestDto(
    @SerialName("userId")
    val userId:Int,
    @SerialName("name")
    val name:String,
    @SerialName("dietType")
    val dietType:String,
    @SerialName("dietTime")
    @Contextual
    val dietTime: String,
    @SerialName("foods")
    val foods: List<FoodRequestDto>
)

@Serializable
data class FoodRequestDto(
    @SerialName("foodId")
    val foodId: Int,
    @SerialName("quantity")
    val quantity: Float
)

@Serializable
data class DietRequestDto(
    @SerialName("userId")
    val userId:Int,
    @SerialName("name")
    val name:String,
    @SerialName("foods")
    val foods: List<FoodRequestDto>
)

@Serializable
data class WeightRequestDto(
    @SerialName("userId") val userId: Int,
    @SerialName("weight") val weight: Float
)




