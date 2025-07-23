package com.konkuk.kuit_kac.di

import androidx.room.Insert
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.service.FoodService
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesFoodRepository (foodDao: FoodDao): FoodRepository = FoodRepository(foodDao)
}