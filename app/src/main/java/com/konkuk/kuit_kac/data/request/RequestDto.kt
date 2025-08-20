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
data class RecordRequestDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("routineType")
    val routineType: String,
    @SerialName("routineExercises")
    val routineExercises: List<ExerciseRequestDto>
)
@Serializable
data class ExerciseRequestDto(
    @SerialName("exerciseId")
    val exerciseId: Int,
    @SerialName("routineDetail")
    val routineDetail: RoutineDetailDto,
    @SerialName("routineSets")
    val routineSets: List<RoutineSetsDto>
)
@Serializable
data class RoutineDetailDto(
    @SerialName("time")
    val time:Int,
    @SerialName("intensity")
    val intensity:String
)
@Serializable
data class RoutineSetsDto(
    @SerialName("count")
    val count: Int,
    @SerialName("weightKg")
    val weightKg: Int,
    @SerialName("weightNum")
    val weightNum: Int,
    @SerialName("distance")
    val distance: Int,
    @SerialName("time")
    val time:Double,
    @SerialName("setOrder")
    val setOrder: Int,
)

@Serializable
data class FitnessRequestDto(
    @SerialName("exerciseId")
    val exerciseId: Int
)

@Serializable
data class SimpleRequestDto(
    @SerialName("userId")
    val userId:Int,
    @SerialName("dietType")
    val dietType:String,
    @SerialName("dietEntryType")
    val dietEntryType: String = "단식"
)

@Serializable
data class SnackRequestDto(
    @SerialName("userId")
    val userId:Int,
    @SerialName("name")
    val name:String,
    @SerialName("foods")
    val foods: List<SnackFoodRequestDto>
)

@Serializable
data class SnackFoodRequestDto(
    @SerialName("foodId")
    val foodId: Int,
    @SerialName("quantity")
    val quantity: Float,
    @SerialName("dietTime")
    val dietTime: String
)

@Serializable
data class PlanRequestDto(
    @SerialName("userId")
    val userId:Int,
    @SerialName("dietType")
    val dietType:String,
    @SerialName("foods")
    val foods: List<FoodRequestDto>,
    @SerialName("date")
    val date: String
)

@Serializable
data class WeightRequestDto(
    @SerialName("userId") val userId: Int,
    @SerialName("weight") val weight: Float
)

@Serializable
data class AiRequestDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("dietActivities")
    val dietActivities: List<AiDto>
)

@Serializable
data class AiDto(
    @SerialName("dietDate")
    val dietDate: String,
    @SerialName("dietType")
    val dietType: String,
    @SerialName("dietEntryType")
    val dietEntryType: String
)

@Serializable
data class OnboardingRequestDto(
    @SerialName("age")
    val age: Int,
    @SerialName("agreements")
    val agreements: List<Agreement>,
    @SerialName("appetiteType")
    val appetiteType: String,
    @SerialName("dietFailReason")
    val dietFailReason: String,
    @SerialName("dietVelocity")
    val dietVelocity: String,
    @SerialName("eatingOutType")
    val eatingOutType: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("hasDietExperience")
    val hasDietExperience: Boolean,
    @SerialName("height")
    val height: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("targetWeight")
    val targetWeight: Double,
    @SerialName("currentWeight")
    val currentWeight: Double,
    @SerialName("activity")
    val activity: String,
    @SerialName("weeklyEatingOutCount")
    val weeklyEatingOutCount: String
)
@Serializable
data class Agreement(
    @SerialName("agreed")
    val agreed: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("version")
    val version: String
)



