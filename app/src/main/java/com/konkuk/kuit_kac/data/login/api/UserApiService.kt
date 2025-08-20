package com.konkuk.kuit_kac.data.login.api

import com.konkuk.kuit_kac.data.login.dto.MintTokenResponseDto
import com.konkuk.kuit_kac.data.login.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("dev-auth/mint")
    suspend fun mintToken(@Query("kid") uid: String): MintTokenResponseDto

    @GET("users/me")
    suspend fun getMe(): UserResponseDto
}
