package com.konkuk.kuit_kac.data.login

import com.konkuk.kuit_kac.presentation.navigation.Route

sealed class NavigationEvent(val route: String) {
    // 온보딩 화면으로 이동하는 이벤트
    object NavigateToOnboarding : NavigationEvent(Route.OnboardingStart.route)

    // 홈 화면으로 이동하는 이벤트
    object NavigateToHome : NavigationEvent(Route.Home.route)
}