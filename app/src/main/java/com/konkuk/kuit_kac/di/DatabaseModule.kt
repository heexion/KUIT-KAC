package com.konkuk.kuit_kac.di

import android.content.Context
import androidx.room.Room
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.local.FoodDatabase
import com.konkuk.kuit_kac.local.dao.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            "food.db"
        ).build()
    }

    @Provides
    fun provideFoodDao(database: FoodDatabase): FoodDao {
        return database.foodDao()
    }
}