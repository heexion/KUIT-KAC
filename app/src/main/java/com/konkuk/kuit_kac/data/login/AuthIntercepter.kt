package com.konkuk.kuit_kac.data.login

import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = runBlocking {
            dataStoreRepository.getAccessToken()
        }

        val requestBuilder = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")

        return chain.proceed(requestBuilder.build())
    }

}

