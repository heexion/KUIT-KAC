package com.konkuk.kuit_kac.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food",
    indices = [Index(value = ["name"], unique = true)]
)
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val unit_type: String = "개",
    val unit_num: Int,
    val food_type: String,
    val is_processed_food: Boolean,
    val calorie: Double,
    val carbohydrate: Double,
    val protein: Double,
    val fat: Double,
    val sugar: Double = 0.0,
    val score: Int = 0
)
