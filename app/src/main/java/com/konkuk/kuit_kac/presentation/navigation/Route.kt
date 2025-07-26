package com.konkuk.kuit_kac.presentation.navigation

sealed class Route(val route: String) {

    data object Diet : Route(route = "diet")       // 식단
    data object DietCreate : Route(route = "DietCreate")
    data object DietExist : Route(route = "DietExist")
    data object DietPatch : Route(route = "DietPatch")
    data object DietAdd : Route(route = "DietAdd")

    data object Meal : Route(route = "meal")
    data object MealExist: Route(route = "MealExist") // 나중에 삭제
    data object MealPatch: Route(route = "MealPatch")
    data object MealFastingResult: Route(route = "MealFastingResult")
    data object MealRecord: Route(route = "MealRecord")
    data object MealSearch: Route(route = "MealSearch")
    data object MealTime: Route(route = "MealTime")

    data object PlanDiet : Route(route = "PlanDiet") // 식단 계획
    data object PlanAI : Route(route = "PlanAI") // 식단 AI 추천
    data object PlanInPerson : Route(route = "PlanInPerson") // 식단 직접 입력
    data object PlanCheck : Route(route = "PlanCheck") // 저장된 식단 확인


    data object Home : Route(route = "home")       // 홈
    data object HomeObservation : Route(route = "homeObservation") // 홈-관찰 일지
    data object HomeScale : Route(route = "homeScale") // 홈-체중계
    data object HomeScaleInput : Route(route = "homeScaleInput") // 홈-체중계 입력
    data object HomeResult : Route(route = "homeResult") // 홈-결과

    data object HomeAnalysis : Route(route = "homeAnalysis")
    data object HomeNutrition : Route(route = "homeNutrition")

    data object Fitness : Route(route = "fitness") // 운동
    data object FitnessExist: Route(route = "FitnessExist")
    data object FitnessCreate: Route(route = "FitnessCreate")
    data object FitnessSearch: Route(route = "FitnessSearch")
    data object FitnessEdit: Route(route = "FitnessEdit")
    data object FitnessEditResult: Route(route = "FitnessEditResult")
    data object FitnessRecordEdit: Route(route =  "fitness_record_edit")
    data object FitnessRoutineEdit : Route(route = "fitness_routine_edit")
    data class FitnessDetailRecord(val name: String) : Route("fitness/detail/$name")
    data object FitnessRecordResult : Route(route = "fitness_record_result")
    data object FitnessRoutineSearch : Route(route = "fitness_routine_search")
    data object FitnessFastInput : Route(route = "fitness_fast_input")
    data object FitnessDetailRecordAdd : Route("fitness_detail_add")







}