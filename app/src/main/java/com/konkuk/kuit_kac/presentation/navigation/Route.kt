package com.konkuk.kuit_kac.presentation.navigation

sealed class Route(val route: String) {

    data object Diet : Route(route = "diet")       // 식단
    data object Home : Route(route = "home")       // 홈
    data object Fitness : Route(route ="fitness") // 운동
}