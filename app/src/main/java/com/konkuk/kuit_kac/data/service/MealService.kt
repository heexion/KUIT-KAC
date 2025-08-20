package com.konkuk.kuit_kac.data.service

import com.konkuk.kuit_kac.data.AiGeneralDto
import com.konkuk.kuit_kac.data.request.AiRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.request.PlanRequestDto
import com.konkuk.kuit_kac.data.request.SimpleRequestDto
import com.konkuk.kuit_kac.data.request.SnackRequestDto
import com.konkuk.kuit_kac.data.response.AiFoodResponse
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.response.PlanResponseDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MealService {
    @POST("diets/records")
    suspend fun createMeal(
        @Body request: MealRequestDto
    ): Response<Unit>

    @GET("diets/records/profiles")
    suspend fun getRecord(
        @Query("userId") userId: Int
    ): Response<List<MealResponseDto>>

    @PUT("diets/records/{dietId}")
    suspend fun changeRecord(
        @Path("dietId") dietId: Int,
        @Body request: MealRequestDto
    ): Response<Unit>

    @POST("diets/simple")
    suspend fun createSimple(
        @Body request: SimpleRequestDto
    ): Response<Unit>

    @DELETE("diets/{dietId}")
    suspend fun deleteMeal(
        @Path("diet") dietId: Int
    )

    @POST("diets/snacks")
    suspend fun createSnack(
        @Body request: SnackRequestDto
    ): Response<Unit>
    @PUT("diets/snacks/{dietId}")
    suspend fun changeSnack(
        @Path("dietId") dietId: Int,
        @Body request: SnackRequestDto
    ): Response<Unit>

    @POST("diets/plans")
    suspend fun createPlan(
        @Body request: PlanRequestDto
    ): Response<Unit>

    @PUT("diets/plans/{dietId}")
    suspend fun changePlan(
        @Path("dietId") dietId: Int,
        @Body request: PlanRequestDto
    ): Response<Unit>

    @GET("diets/plans/profiles")
    suspend fun getPlan(
        @Query("userId") userId: Int
    ): Response<List<PlanResponseDto>>

    @POST("ai/diets")
    suspend fun postAi(
        @Body request: AiRequestDto
    ): Response<AiGeneralDto>
    @POST("ai/diets/create")
    suspend fun createAi(
        @Body request: AiGeneralDto
    ): Response<Unit>

    @GET("foods")
    suspend fun getNewAiFood(

    ): Response<List<AiFoodResponse>>

    @GET("diets/activities/months")
    suspend fun getMonthPlan(
        @Query("userId") userId: Int,
        @Query("yearMonth") yearMonth: String
    ): Response<List<PlanResponseDto>>
}