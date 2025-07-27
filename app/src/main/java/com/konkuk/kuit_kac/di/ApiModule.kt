package com.konkuk.kuit_kac.di

import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.local.service.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): FoodService =
        retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun providesDietService(retrofit: Retrofit): DietService =
        retrofit.create(DietService::class.java)
}