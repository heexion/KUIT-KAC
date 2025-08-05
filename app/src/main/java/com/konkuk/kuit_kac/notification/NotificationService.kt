package com.konkuk.kuit_kac.notification

import android.Manifest
import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.konkuk.kuit_kac.R

class MyNotificationListenerService : NotificationListenerService() {
    private var lastAlertTime = 0L

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val senderPackage = sbn.packageName
        when (senderPackage) {
            "com.sampleapp",
            "com.shinhan.o2o",
            "com.coupang.mobile.eats",
            "com.google.android.gm"-> {
                val title = sbn.notification.extras.getString(Notification.EXTRA_TITLE) ?: ""
                val text = sbn.notification.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
                val combined = "$title $text"

                if (combined.contains("접수")) {
                    val now = System.currentTimeMillis()
                    if (now - lastAlertTime < 30_000) return
                    lastAlertTime = now
                    val builder = NotificationCompat.Builder(this, "alert_channel")
                        .setSmallIcon(R.drawable.ic_jam)
                        .setContentTitle("냠코치")
                        .setContentText("야 배달 뭐야? 한 번만 봐줄게 기록해놔")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)

                    NotificationManagerCompat.from(this).notify(2001, builder.build())
                }
            }

            else -> {
                Log.d("FilteredNotif", "Ignored: $senderPackage")
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.d("NotifListener", "Notification removed: ${sbn.packageName}")
    }
}