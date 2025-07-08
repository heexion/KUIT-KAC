package com.konkuk.kuit_kac.presentation.navigation

sealed class Route(val route: String) {

    data object Diet : Route(route = "diet")       // 식단

    data object Home : Route(route = "home")       // 홈
    data object HomeObservation : Route(route = "homeObservation") // 홈-관찰 일지
    data object HomeScale : Route(route = "homeScale") // 홈-체중계
    data object HomeScaleInput : Route(route = "homeScaleInput") // 홈-체중계 입력
    data object HomeResult : Route(route = "homeResult") // 홈-결과

    data object HomeAnalysis: Route(route = "homeAnalysis")
    data object HomeNutrition: Route(route = "homeNutrition")

    data object Fitness : Route(route = "fitness") // 운동

}