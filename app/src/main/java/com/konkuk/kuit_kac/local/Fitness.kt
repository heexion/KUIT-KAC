package com.konkuk.kuit_kac.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitness")
data class Fitness (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val targetMuscleGroup: String,
    val metValue: Double,
    val type: Int
)