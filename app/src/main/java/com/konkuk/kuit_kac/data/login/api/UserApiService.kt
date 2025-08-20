package com.konkuk.kuit_kac.data.login.api

import retrofit2.http.GET

interface UserApiService {
    @GET("users/me")
    suspend fun getMe(): Map<String, String>
}
