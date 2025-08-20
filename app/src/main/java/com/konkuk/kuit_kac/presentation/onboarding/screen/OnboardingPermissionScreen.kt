package com.konkuk.kuit_kac.presentation.onboarding.screen



import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingMainHomeHam
import com.konkuk.kuit_kac.presentation.onboarding.component.OnboardingButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun isNotificationServiceEnabled(context: Context): Boolean {
    val pkgName = context.packageName
    val flat = Settings.Secure.getString(
        context.contentResolver,
        "enabled_notification_listeners"
    )
    return flat?.contains(pkgName) == true
}

fun hasPostNotificationPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else true
}

fun isBatteryOptimizationIgnored(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        pm.isIgnoringBatteryOptimizations(context.packageName)
    } else true
}

fun areAppNotificationsEnabled(context: Context): Boolean {
    return NotificationManagerCompat.from(context).areNotificationsEnabled()
}

fun canScheduleExactAlarms(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.canScheduleExactAlarms()
    } else true
}


@Composable
fun OnboardingPermissionScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val requestPostNoti = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            Toast.makeText(context, "알림 권한이 필요해요.", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
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
                    bottom = 25f.bhp() + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(20f.bhp())
        ) {
            OnboardingButton(
                value = "권한 설정하러 가기",
                onClick = {
                    // 권한 요청 → 다 끝날 때까지 대기
                    scope.launch {
                        while (
                            !hasPostNotificationPermission(context) ||
                            !isBatteryOptimizationIgnored(context) ||
                            !areAppNotificationsEnabled(context) ||
                            !canScheduleExactAlarms(context) ||
                            !isNotificationServiceEnabled(context)
                        ) {
                            // 권한 하나라도 빠져 있으면 설정창 열어줌
                            if (!hasPostNotificationPermission(context) &&
                                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                            ) {
                                requestPostNoti.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                            if (!isBatteryOptimizationIgnored(context) &&
                                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                            ) {
                                context.startActivity(
                                    Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                                        data = Uri.parse("package:${context.packageName}")
                                    }
                                )
                            }
                            if (!areAppNotificationsEnabled(context)) {
                                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                                }
                                context.startActivity(intent)
                            }
                            if (!canScheduleExactAlarms(context) &&
                                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                            ) {
                                context.startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                            }
                            if (!isNotificationServiceEnabled(context)) {
                                context.startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
                            }

                            delay(1000) // 1초마다 다시 체크
                        }

                        // 모든 권한 허용 완료 시
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