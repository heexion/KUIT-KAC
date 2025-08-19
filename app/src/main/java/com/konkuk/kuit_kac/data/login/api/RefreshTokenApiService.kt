package com.konkuk.kuit_kac.data.login.api

import com.konkuk.kuit_kac.data.login.dto.RefreshTokenRequestDto
import com.konkuk.kuit_kac.data.login.dto.RefreshTokenResponseDto
import com.konkuk.kuit_kac.data.login.dto.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// refresh 로그인 api
interface RefreshTokenApiService {
    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequestDto
    ): RefreshTokenResponseDto
}

interface UserApiService {
    @GET("users/me")
    suspend fun getMe(): UserResponseDto
}



