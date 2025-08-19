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
    val foodType: String?,
    val calorie: Double,
    val protein: Double,
    val fat: Double,
    val carb: Double
)