package com.konkuk.kuit_kac.notification

import android.content.Context
import android.provider.Settings

fun isNotificationServiceEnabled(context: Context): Boolean {
    val enabledListeners = Settings.Secure.getString(
        context.contentResolver,
        "enabled_notification_listeners"
    )
    return enabledListeners?.contains(context.packageName) == true
}