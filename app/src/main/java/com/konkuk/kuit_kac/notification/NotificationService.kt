package com.konkuk.kuit_kac.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.konkuk.kuit_kac.MainActivity
import com.konkuk.kuit_kac.R

class MyNotificationListenerService : NotificationListenerService() {
    private var lastAlertTime = 0L

    private val allowedChatRooms = setOf(
        "배달의민족",
        "쿠팡이츠 알림"
    )
    private val roomKeywordFilters: Map<String, Regex> = mapOf(
        "배달의민족" to Regex("접수|주문|배달", RegexOption.IGNORE_CASE),
        "쿠팡이츠 알림" to Regex("접수|주문|배달", RegexOption.IGNORE_CASE)
    )

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val flags = sbn.notification.flags
        if ((flags and Notification.FLAG_ONGOING_EVENT) != 0) return
        if ((flags and Notification.FLAG_GROUP_SUMMARY) != 0) return

        when (sbn.packageName) {
            "com.sampleapp",
            "com.shinhan.o2o",
            "com.coupang.mobile.eats",
            "com.google.android.gm",
            "com.kakao.talk" -> {
                val extras = sbn.notification.extras

                val chatRoom = (
                        extras.getString(Notification.EXTRA_CONVERSATION_TITLE)
                            ?: extras.getString(Notification.EXTRA_TITLE)
                            ?: extras.getString("android.title")
                        )?.trim().orEmpty()

                val textSingle = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
                val textLines = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    extras.getCharSequenceArray(Notification.EXTRA_TEXT_LINES)
                        ?.joinToString("\n") { it.toString() } ?: ""
                } else ""
                val messageText = (if (textLines.isNotBlank()) textLines else textSingle).trim()

                val isAllowedRoom = chatRoom in allowedChatRooms
                val globalReceiptHit = Regex("접수", RegexOption.IGNORE_CASE).containsMatchIn(messageText)
                val perRoomRegex = roomKeywordFilters[chatRoom]
                val perRoomHit = perRoomRegex?.containsMatchIn(messageText) ?: true

                if (isAllowedRoom && globalReceiptHit && perRoomHit) {
                    val now = System.currentTimeMillis()
                    if (now - lastAlertTime >= 30_000) {
                        lastAlertTime = now
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra("from_alert", true)
                        }

                        val pendingIntent = TaskStackBuilder.create(this).run {
                            addNextIntentWithParentStack(intent)
                            getPendingIntent(
                                2001,
                                (PendingIntent.FLAG_UPDATE_CURRENT or
                                        (PendingIntent.FLAG_IMMUTABLE))
                            )
                        }

                        val builder = NotificationCompat.Builder(this, "alert_channel")
                            .setSmallIcon(R.drawable.ic_jam)
                            .setContentTitle("냠코치")
                            .setContentText("야 배달 뭐야? 한 번만 봐줄게 기록해놔")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)

                        NotificationManagerCompat.from(this).notify(2001, builder.build())
                    }
                } else {
                    Log.d("FilteredNotif", "Ignored Kakao: room='$chatRoom' text='$messageText'")
                }
            }
            else -> Log.d("FilteredNotif", "Ignored: ${sbn.packageName}")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.d("NotifListener", "Notification removed: ${sbn.packageName}")
    }
}
