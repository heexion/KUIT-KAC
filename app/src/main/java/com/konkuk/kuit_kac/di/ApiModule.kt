package com.konkuk.kuit_kac.di

import com.konkuk.kuit_kac.BuildConfig
import com.konkuk.kuit_kac.data.login.AuthAuthenticator
import com.konkuk.kuit_kac.data.login.AuthInterceptor
import com.konkuk.kuit_kac.data.login.api.RefreshTokenApiService
import com.konkuk.kuit_kac.data.login.api.UserApiService
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.service.CoachReportApiService
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.data.service.HomeSummaryApiService
import com.konkuk.kuit_kac.data.service.HomeWeightService
import com.konkuk.kuit_kac.data.service.MealService
import com.konkuk.kuit_kac.data.service.RoutineService
import com.konkuk.kuit_kac.data.service.NutritionService
import com.konkuk.kuit_kac.data.service.OnboardingService
import com.konkuk.kuit_kac.local.service.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(dataStoreRepository: DataStoreRepository): Interceptor {
        return AuthInterceptor(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        dataStoreRepository: DataStoreRepository,
        refreshTokenService: dagger.Lazy<RefreshTokenApiService>
    ): AuthAuthenticator {
        return AuthAuthenticator(
            dataStoreRepository = dataStoreRepository,
            refreshTokenApiService = refreshTokenService
        )
    }

    @Provides
    @Singleton
    fun provideRefreshTokenApiService(retrofit: Retrofit): RefreshTokenApiService =
        retrofit.create(RefreshTokenApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

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

    @Provides
    @Singleton
    fun provideRoutineService(retrofit: Retrofit): RoutineService =
        retrofit.create(RoutineService::class.java)

    @Provides
    @Singleton
    fun provideNutritionService(retrofit: Retrofit): NutritionService =
        retrofit.create(NutritionService::class.java)

    @Provides
    @Singleton
    fun provideHomeSummaryApiService(retrofit: Retrofit): HomeSummaryApiService {
        return retrofit.create(HomeSummaryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoachReportApiService(retrofit: Retrofit): CoachReportApiService =
        retrofit.create(CoachReportApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeWeightService(retrofit: Retrofit): HomeWeightService =
        retrofit.create(HomeWeightService::class.java)

    @Provides
    @Singleton
    fun provideOnboardingService(retrofit: Retrofit): OnboardingService =
        retrofit.create(OnboardingService::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .authenticator(authAuthenticator)
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


