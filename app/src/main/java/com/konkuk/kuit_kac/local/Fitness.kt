package com.konkuk.kuit_kac.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fitness",
    indices = [Index(value = ["name"])]
)
data class Fitness(
    @PrimaryKey val id: Int,
    val name: String,
    val targetMuscleGroup: String,
    val metValue: Double
)