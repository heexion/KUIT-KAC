package com.konkuk.kuit_kac.data.response


import com.konkuk.kuit_kac.data.response.RoutineResponseDto.RoutineExerciseProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RoutineResponseDto(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("routineExerciseProfiles")
    val routineExerciseProfiles: List<RoutineExerciseProfile>,
    @SerialName("routineType")
    val routineType: String,
    @SerialName("updatedAt")
    val updatedAt: String
) {
    @Serializable
    data class RoutineExerciseProfile(
        @SerialName("exercise")
        val exercise: Exercise,
        @SerialName("id")
        val id: Int
    ) {
        @Serializable
        data class Exercise(
            @SerialName("createdAt")
            val createdAt: String,
            @SerialName("id")
            val id: Int,
            @SerialName("metValue")
            val metValue: Double,
            @SerialName("name")
            val name: String,
            @SerialName("targetMuscleGroup")
            val targetMuscleGroup: String,
            @SerialName("updatedAt")
            val updatedAt: String
        )
    }
}
