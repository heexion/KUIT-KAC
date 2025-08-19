package com.konkuk.kuit_kac.di

import com.konkuk.kuit_kac.data.service.CoachReportApiService
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.data.service.HomeSummaryApiService
import com.konkuk.kuit_kac.data.service.HomeWeightService
import com.konkuk.kuit_kac.data.service.MealService
import com.konkuk.kuit_kac.data.service.RoutineService
import com.konkuk.kuit_kac.local.dao.FitnessDao
import com.konkuk.kuit_kac.data.service.NutritionService
import com.konkuk.kuit_kac.data.service.OnboardingService
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.service.FoodService
import com.konkuk.kuit_kac.presentation.fitness.RoutineRepository
import com.konkuk.kuit_kac.presentation.fitness.RoutineRepositoryImpl
import com.konkuk.kuit_kac.presentation.fitness.local.FitnessRepository
import com.konkuk.kuit_kac.presentation.home.repository.CoachReportRepository
import com.konkuk.kuit_kac.presentation.home.repository.CoachReportRepositoryImpl
import com.konkuk.kuit_kac.presentation.home.repository.HomeSummaryRepository
import com.konkuk.kuit_kac.presentation.home.repository.HomeSummaryRepositoryImpl
import com.konkuk.kuit_kac.presentation.home.repository.HomeWeightRepository
import com.konkuk.kuit_kac.presentation.home.repository.HomeWeightRepositoryImpl
import com.konkuk.kuit_kac.presentation.home.repository.NutritionRepository
import com.konkuk.kuit_kac.presentation.home.repository.NutritionRepositoryImpl
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepositoryImpl
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepositoryImpl
import com.konkuk.kuit_kac.presentation.onboarding.OnboardingRepository
import com.konkuk.kuit_kac.presentation.onboarding.OnboardingRepositoryImpl
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

    @Provides
    @Singleton
    fun provideNutritionRepository(
        nutritionService: NutritionService
    ): NutritionRepository = NutritionRepositoryImpl(nutritionService)

    @Provides
    @Singleton
    fun provideCoachReportRepository(
        coachReportApiService: CoachReportApiService
    ): CoachReportRepository = CoachReportRepositoryImpl(coachReportApiService)

    @Provides
    @Singleton
    fun provideHomeSummaryRepository(
        apiService: HomeSummaryApiService
    ): HomeSummaryRepository = HomeSummaryRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideHomeWeightRepository(
        api: HomeWeightService
    ): HomeWeightRepository = HomeWeightRepositoryImpl(api)
    @Provides
    @Singleton
    fun provideOnboardingRepository(
        api: OnboardingService
    ): OnboardingRepository = OnboardingRepositoryImpl(api)
}