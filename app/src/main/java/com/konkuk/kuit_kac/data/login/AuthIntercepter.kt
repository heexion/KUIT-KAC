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
//    private val dataStore: DataStore<Preferences>,
//    private val refreshTokenApiService: dagger.Lazy<RefreshTokenApiService> // Lazy 적용
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = runBlocking {
            dataStoreRepository.getAccessToken()
        }

        val requestBuilder = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")

        return chain.proceed(requestBuilder.build())

        /*
        var accessToken: String = runBlocking {
            dataStore.data.first()[TokenKeys.ACCESS_TOKEN] ?: ""
        }

        var request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        var response = chain.proceed(request)

        if (response.code == 401) {
            response.close()

            val refreshToken = runBlocking {
                dataStore.data.first()[TokenKeys.REFRESH_TOKEN] ?: ""
            }

            try {
                val refreshResponse = runBlocking {
                    refreshTokenApiService.get().refreshToken(RefreshRequest(refreshToken))
                }

                runBlocking {
                    dataStore.edit { prefs ->
                        prefs[TokenKeys.ACCESS_TOKEN] = refreshResponse.accessToken
                        prefs[TokenKeys.REFRESH_TOKEN] = refreshResponse.refreshToken
                    }
                }

                accessToken = refreshResponse.accessToken

                val newRequest = chain.request().newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()

                response = chain.proceed(newRequest)

            } catch (e: Exception) {
                return response
            }
        }

        return response
    }*/
    }

}

