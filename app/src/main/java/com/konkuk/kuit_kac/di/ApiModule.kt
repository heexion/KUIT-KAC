package com.konkuk.kuit_kac.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.konkuk.kuit_kac.BuildConfig
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.data.service.MealService
import com.konkuk.kuit_kac.local.service.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMealService(retrofit: Retrofit): MealService =
        retrofit.create(MealService::class.java)

    @Provides
    @Singleton
    fun provideDietService(retrofit: Retrofit): DietService =
        retrofit.create(DietService::class.java)

    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): FoodService =
        retrofit.create(FoodService::class.java)
}
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
