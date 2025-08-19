package com.konkuk.kuit_kac.data.login

import com.konkuk.kuit_kac.data.login.api.RefreshTokenApiService
import com.konkuk.kuit_kac.data.login.dto.RefreshTokenRequestDto
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenRefreshService: Lazy<RefreshTokenApiService>
    private val dataStoreRepository: DataStoreRepository,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code != 401) {
            return null
        }
        synchronized(this) {
            val accessToken = runBlocking { dataStoreRepository.getAccessToken() }
            val refreshToken = runBlocking { dataStoreRepository.getRefreshToken() }

            val oldAccessToken = response.request.header("Authorization")?.removePrefix("Bearer ")
            if (oldAccessToken != null && oldAccessToken != accessToken) {
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
            }

            if (refreshToken == null) {
                return null
            }

            // 토큰 갱신
            val refreshResponse = runBlocking {
                tokenRefreshService.get().refreshToken(RefreshTokenRequestDto(refreshToken))
            }

            if (refreshResponse.refreshToken == null || refreshResponse.accessToken == null) {
                // 토큰 갱신 실패
                response.close()
                return null
            } else {
                // 토큰 갱신 성공
                val newAccessTokens = refreshResponse.accessToken
                val newRefreshTokens = refreshResponse.refreshToken

                runBlocking {
                    dataStoreRepository.saveAccessToken(newAccessTokens)
                    dataStoreRepository.saveRefreshToken(newRefreshTokens)
                }
                return newRequestWithToken(newAccessTokens, response.request)
            }
        }
    }

    private fun newRequestWithToken(accessToken: String, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}