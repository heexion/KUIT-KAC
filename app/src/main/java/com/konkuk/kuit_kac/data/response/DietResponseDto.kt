package com.konkuk.kuit_kac.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DietResponseDto : ArrayList<DietResponseDto.DietResponseDtoItem>(){
    @Serializable
    data class DietResponseDtoItem(
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("dietEntryType")
        val dietEntryType: String,
        @SerialName("dietFoods")
        val dietFoods: List<DietFood>,
        @SerialName("dietType")
        val dietType: String,
        @SerialName("foodStatusType")
        val foodStatusType: String,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("totalKcal")
        val totalKcal: Int,
        @SerialName("updatedAt")
        val updatedAt: String
    ) {
        @Serializable
        data class DietFood(
            @SerialName("dietTime")
            val dietTime: String,
            @SerialName("food")
            val food: Food,
            @SerialName("id")
            val id: Int,
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
        @Serializable
        data class DietAiFood(
            @SerialName("dietTime")
            val dietTime: String,
            @SerialName("food")
            val food: Food,
            @SerialName("id")
            val id: Int,
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