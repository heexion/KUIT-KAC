package com.konkuk.kuit_kac.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


    @Serializable
    data class RecordResponseDto(
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
            val id: Int,
            @SerialName("routineDetail")
            val routineDetail: RoutineDetail,
            @SerialName("routineSets")
            val routineSets: List<RoutineSet>
        ) {
            @Serializable
            data class Exercise(
                @SerialName("createdAt")
                val createdAt: String,
                @SerialName("id")
                val id: Int,
                @SerialName("metValue")
                val metValue: Int,
                @SerialName("name")
                val name: String,
                @SerialName("targetMuscleType")
                val targetMuscleType: String,
                @SerialName("updatedAt")
                val updatedAt: String
            )
    
            @Serializable
            data class RoutineDetail(
                @SerialName("createdAt")
                val createdAt: String,
                @SerialName("id")
                val id: Int,
                @SerialName("intensity")
                val intensity: String,
                @SerialName("routineExerciseId")
                val routineExerciseId: Int,
                @SerialName("time")
                val time: Int,
                @SerialName("updatedAt")
                val updatedAt: String
            )
    
            @Serializable
            data class RoutineSet(
                @SerialName("count")
                val count: Int,
                @SerialName("createdAt")
                val createdAt: String,
                @SerialName("distance")
                val distance: Int,
                @SerialName("id")
                val id: Int,
                @SerialName("routineExerciseId")
                val routineExerciseId: Int,
                @SerialName("setOrder")
                val setOrder: Int,
                @SerialName("time")
                val time: Double,
                @SerialName("updatedAt")
                val updatedAt: String,
                @SerialName("weightKg")
                val weightKg: Int,
                @SerialName("weightNum")
                val weightNum: Int
            )
        }
    }
