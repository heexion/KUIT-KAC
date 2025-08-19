package com.konkuk.kuit_kac.presentation.navigation

sealed class Route(val route: String) {

    data object Diet : Route(route = "diet")       // 식단
    data object DietCreate : Route(route = "DietCreate")
    data object DietExist : Route(route = "DietExist")
    data object DietPatch : Route(route = "DietPatch")
    data object DietSearch : Route(route = "DietSearch")

    data object DietEditSearchItem : Route(route = "DietEditSearchItem")
    data object DietEditSerch : Route(route = "DietEditSerch")
    data object DietEditTemp : Route(route = "DietEditTemp")
    data object DietMain : Route(route = "DietMain")
    data object DietSearchItemDetail : Route(route = "DietSearchItemDetail")

    data object Meal : Route(route = "meal")
    data object MealExist: Route(route = "MealExist") // 나중에 삭제
    data object MealPatch: Route(route = "MealPatch")
    data object MealFastingResult: Route(route = "MealFastingResult")
    data object MealTime: Route(route = "MealTime")
    data object MealRecord: Route(route = "MealRecord")


    //MEAL GRAPH
    data object MealGraph: Route(route = "MealGraph/{mealType}")
    data object MealSearch: Route(route = "MealSearch")
    data object MealTemp: Route(route = "MealTemp")
    data object MealEditTemp: Route(route = "MealEditTemp")

    data object MealEditResult : Route(route = "MealEditResult")
    data object MealEditSearchItem : Route(route = "MealEditSearchItem")
    data object MealEditSearch : Route(route = "MealEditSearch")
    data object MealEditTime : Route(route = "MealEditTime")
    data object MealMain : Route(route = "MealMain")
    data object MealSearchItemDetail : Route(route = "MealSearchItemDetail")
    data object TimeInputResult : Route(route = "TimeInputResult")

    data object PlanDiet : Route(route = "PlanDiet") // 식단 계획
    data object PlanAI : Route(route = "PlanAI") // 식단 AI 추천
    data object PlanInPerson : Route(route = "PlanInPerson") // 식단 직접 입력
    data object PlanCheck : Route(route = "PlanCheck") // 저장된 식단 확인

    // Plan AI 관련
    data object PlanAIComplete : Route(route = "PlanAIComplete")
    data object PlanAIDetail : Route(route = "PlanAIDetail")
    data object PlanAILoading : Route(route = "PlanAILoading")
    data object PlanAIRecom : Route(route = "PlanAIRecom")

    // Plan InPerson (직접 입력) 관련
    data object PlanInPersonAddComplete : Route(route = "PlanInPersonAddComplete")
    data object PlanIPAdd : Route(route = "PlanIPAdd")
    data object PlanIPItem : Route(route = "PlanIPItem")
    data object PlanIPSearch : Route(route = "PlanIPSearch")
    data object PlanIPTemp : Route(route = "PlanIPTemp")

    // 결과 화면
    data object PlanResult : Route(route = "PlanResult")



    data object Home : Route(route = "home")       // 홈
    data object HomeObservation : Route(route = "homeObservation") // 홈-관찰 일지
    data object HomeScale : Route(route = "homeScale") // 홈-체중계
    data object HomeScaleInput : Route(route = "homeScaleInput") // 홈-체중계 입력
    data object HomeResult : Route(route = "homeResult") // 홈-결과

//    data object HomeAnalysis : Route(route = "homeAnalysis")
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
    data object FitnessDetailInput : Route(route = "fitness_detail_input")
    data object FitnessRecordScreen : Route(route = "fitness_record_screen")
    data object FitnessAddDetailRecord : Route(route = "fitness_add_detail_record")
    data object FitnessAddRecordEdit : Route(route = "fitness_add_record_edit")
    data object FitnessRecordMain : Route(route = "fitness_record_main")
    data object FitnessRoutineDetailInput : Route(route = "fitness_routine_detail_input")


    //온보딩
    data object OnboardingStart : Route(route = "onboarding_start")
    data object OnboardingDiet : Route(route = "onboarding_diet")
    data object OnboardingFailEx : Route(route = "onboarding_fail_ex")
    data object OnboardingAppetite : Route(route = "onboarding_appetite")
    data object OnboardingWeek : Route(route = "onboarding_week")
    data object OnboardingPreferType : Route(route = "onboarding_prefer_type")
    data object OnboardingDietSpeed : Route(route = "onboarding_diet_speed")
    data object OnboardingActivityLevel : Route(route = "onboarding_activity_level")
    data object OnboardingInput : Route(route = "onboarding_input")
    data object OnboardingInputResult : Route(route = "onboarding_input_result")
    data object OnboardingIntroduce : Route(route = "onboarding_introduce")
    data object OnboardingHamCoach : Route(route = "onboarding_ham_coach")
    data object OnboardingNyamCoach : Route(route = "onboarding_nyam_coach")
    data object OnboardingDelivery : Route(route = "onboarding_delivery")
    data object OnboardingMainHomeHam : Route(route = "onboarding_main_home_ham")
    data object OnboardingMainHomeNyam : Route(route = "onboarding_main_home_nyam")
    data object OnboardingMainHomeScale : Route(route = "onboarding_main_home_scale")
    data object OnboardingAiMeal : Route(route = "onboarding_ai_meal")
    data object OnboardingMeal : Route(route = "onboarding_meal")
    data object OnboardingAiIntro : Route(route = "onboarding_ai_intro")
    data object OnboardingGray : Route(route = "onboarding_gray")
    data object OnboardingYellow : Route(route = "onboarding_yellow")
    data object OnboardingEoDrink : Route(route = "onboarding_eo_drink")
    data object OnboardingCheck : Route(route = "onboarding_check")
    data object OnboardingFinal : Route(route = "onboarding_final")
    data object OnboardingFloatingButton : Route(route = "onboarding_floating_button")






    data object LoginMain : Route(route = "login_main")
    data object LoginEmail : Route(route = "login_email")








}