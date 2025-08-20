package com.konkuk.kuit_kac.presentation.onboarding.screen



import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeHam
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 현재 앱이 알림 접근 권한을 가지고 있는지 체크
fun isNotificationServiceEnabled(context: Context): Boolean {
    val pkgName = context.packageName
    val flat = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
    return flat?.contains(pkgName) == true
}


@Composable
fun OnboardingPermissionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        // 배경 및 캐릭터
        OnboardingBackScreen(
            bubbleText = "지금까지 소개한\n기능들을 너에게 제공하려면\n권한이 필요해!",
            bubbleFontSize = 24f.isp(),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(),
                    end = 24f.wp(),
                    bottom = 25f.bhp() +
                            WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(20f.bhp())
        ) {
            OnboardingButton(
                value = "권한 설정하러 가기",
                onClick = {
                    // 알림 접근 설정 화면 열기
                    val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                    context.startActivity(intent)

                    // 허용될 때까지 1초마다 반복 체크
                    scope.launch {
                        while (!isNotificationServiceEnabled(context)) {
                            delay(1000)
                        }
                        // 허용되면 다음 화면으로 이동
                        navController.navigate(OnboardingMainHomeHam.route)
                    }
                },
                modifier = Modifier
                    .width(364f.wp())
                    .height(65f.bhp())
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingPermissionScreenPreview() {

    val navController = rememberNavController()
    OnboardingPermissionScreen(navController = navController)
}