package com.konkuk.kuit_kac.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.konkuk.kuit_kac.BuildConfig
import com.konkuk.kuit_kac.data.service.DietService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object ApiFactory {
    private val BASE_URL: String = BuildConfig.BASE_URL

    private val client = OkHttpClient.Builder()
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool{
    val dietService = ApiFactory.create<DietService>()
}