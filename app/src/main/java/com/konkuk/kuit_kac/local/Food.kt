package com.konkuk.kuit_kac.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val calorie: Double,
    val protein: Double,
    val fat: Double,
    val carb: Double,
)