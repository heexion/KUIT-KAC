package com.konkuk.kuit_kac.data.request

import com.konkuk.kuit_kac.local.Food
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.Date

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
data class RoutineRequestDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("routineType")
    val routineType: String,
    @SerialName("routineExercises")
    val routineExercises: List<FitnessRequestDto>
)

@Serializable
data class FitnessRequestDto(
    @SerialName("exerciseId")
    val exerciseId: Int
)