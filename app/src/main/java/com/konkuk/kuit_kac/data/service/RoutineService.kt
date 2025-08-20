package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.request.RecordRequestDto
import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import com.konkuk.kuit_kac.data.response.RecordResponseDto
import com.konkuk.kuit_kac.data.response.RoutineResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutineService {
    @POST("routines")
    suspend fun createRoutine(
        @Body request: RoutineRequestDto
    ): Response<Unit>

    @POST("routines/records")
    suspend fun createRoutineRecord(
        @Body request: RecordRequestDto
    ): Response<Unit>

    @PUT("routines/{routineId}")
    suspend fun changeRoutine(
        @Path("routineId") routineId: Int,
        @Body request: RoutineRequestDto
    ): Response<Unit>
    @GET("routines/records/profiles")
    suspend fun getRoutineRecord(
        @Query("userId") userId: Int
    ): Response<List<RecordResponseDto>>

    @GET("routines/template/profiles")
    suspend fun getRoutineTemplate(
        @Query("userId") userId: Int
    ): Response<List<RoutineResponseDto>>

    @DELETE("routines/{routineId}")
    suspend fun deleteRoutine(
        @Path("routineId") routineId: Int
    )
}