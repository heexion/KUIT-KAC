package com.konkuk.kuit_kac.data.login

import android.util.Log
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

        val request = chain.request()
        val builder = request.newBuilder()


//        val requestBuilder = chain.request().newBuilder()
//            .header("Authorization", "Bearer $accessToken")

        if (!accessToken.isNullOrBlank()) {
            Log.d("AuthInterceptor", "토큰을 성공적으로 읽었습니다: ${accessToken.take(20)}...")
            builder.header("Authorization", "Bearer $accessToken")
        } else {
            Log.w("AuthInterceptor", "토큰이 null이거나 비어있어서 헤더에 추가하지 못했습니다.")
        }
        Log.d("AuthInterceptor", "요청 URL: ${request.url}")

        return chain.proceed(builder.build())
    }

}

