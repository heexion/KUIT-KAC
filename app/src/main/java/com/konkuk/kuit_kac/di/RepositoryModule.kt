package com.konkuk.kuit_kac.di

import androidx.room.Insert
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.data.service.MealService
import com.konkuk.kuit_kac.data.service.RoutineService
import com.konkuk.kuit_kac.local.dao.FitnessDao
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.service.FoodService
import com.konkuk.kuit_kac.presentation.fitness.RoutineRepository
import com.konkuk.kuit_kac.presentation.fitness.RoutineRepositoryImpl
import com.konkuk.kuit_kac.presentation.fitness.local.FitnessRepository
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepositoryImpl
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepositoryImpl
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
    fun provideMealRepository(mealService: MealService): MealRepository =
        MealRepositoryImpl(mealService)

    @Provides
    @Singleton
    fun providesFoodRepository(foodDao: FoodDao): FoodRepository =
        FoodRepository(foodDao)

    @Provides
    @Singleton
    fun providesFitnessRepository(fitnessDao: FitnessDao): FitnessRepository =
        FitnessRepository(fitnessDao)

    @Provides
    @Singleton
    fun providesRoutineRepository(routineService: RoutineService): RoutineRepository =
        RoutineRepositoryImpl(routineService)

    @Provides
    @Singleton
    fun providesDietRepository(dietService: DietService): DietRepository =
        DietRepositoryImpl(dietService)
}