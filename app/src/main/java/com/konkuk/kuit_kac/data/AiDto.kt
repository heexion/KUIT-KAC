package com.konkuk.kuit_kac.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiGeneralDto(
    @SerialName("dietAiPlanGenerateRequest")
    val dietAiPlanGenerateRequest: DietAiPlanGenerateRequest,
    @SerialName("plans")
    val plans: List<Plan>
) {
    @Serializable
    data class DietAiPlanGenerateRequest(
        @SerialName("dietActivities")
        val dietActivities: List<DietActivity>
    ) {
        @Serializable
        data class DietActivity(
            @SerialName("dietDate")
            val dietDate: String,
            @SerialName("dietEntryType")
            val dietEntryType: String,
            @SerialName("dietType")
            val dietType: String
        )
    }

    @Serializable
    data class Plan(
        @SerialName("date")
        val date: String,
        @SerialName("dietType")
        val dietType: String,
        @SerialName("foods")
        val foods: List<Food>,
        @SerialName("userId")
        val userId: Int
    ) {
        @Serializable
        data class Food(
            @SerialName("food")
            val food: Food,
            @SerialName("quantity")
            val quantity: Int
        ) {
            @Serializable
            data class Food(
                @SerialName("calorie")
                val calorie: Int,
                @SerialName("createdAt")
                val createdAt: String,
                @SerialName("foodType")
                val foodType: String,
                @SerialName("id")
                val id: Int,
                @SerialName("isProcessedFood")
                val isProcessedFood: Boolean,
                @SerialName("name")
                val name: String,
                @SerialName("score")
                val score: Int,
                @SerialName("unitType")
                val unitType: String,
                @SerialName("updatedAt")
                val updatedAt: String
            )
        }
    }
}
