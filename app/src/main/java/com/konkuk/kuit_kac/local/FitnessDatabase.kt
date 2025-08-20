package com.konkuk.kuit_kac.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.konkuk.kuit_kac.local.dao.FitnessDao
import com.konkuk.kuit_kac.local.dao.FoodDao

@Database(entities = [Fitness::class], version = 6, exportSchema = false)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao

}