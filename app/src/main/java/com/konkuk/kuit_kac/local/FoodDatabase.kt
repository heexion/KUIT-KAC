package com.konkuk.kuit_kac.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.konkuk.kuit_kac.local.dao.FoodDao

@Database(entities = [Food::class], version = 6, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}