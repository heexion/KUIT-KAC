package com.konkuk.kuit_kac.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.konkuk.kuit_kac.MainActivity
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.local.dao.FoodDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate
import java.util.Calendar

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
                            .setSmallIcon(R.drawable.img_nyamee_normal)
                            .setContentTitle("냠코치")
                            .setContentText("배달시킨거야? 기록으로 남겨줘")
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

object ReminderScheduler {

    const val ACTION_MORNING = "com.konkuk.kuit_kac.action.MORNING"
    const val ACTION_LUNCH = "com.konkuk.kuit_kac.action.LUNCH"
    const val ACTION_DINNER = "com.konkuk.kuit_kac.action.DINNER"
    const val ACTION_LATE_SNACK_WARN = "com.konkuk.kuit_kac.action.LATE_SNACK_WARN"
    const val ACTION_CHECK_MISSING = "com.konkuk.kuit_kac.action.CHECK_MISSING"

    fun ensureAllReminders(context: Context) {
        // one-shot exact alarms; we re-schedule each day when they fire
        scheduleExactDaily(context, 9, 0,  1001, ACTION_MORNING)
        scheduleExactDaily(context, 12, 0, 1002, ACTION_LUNCH)
        scheduleExactDaily(context, 18, 0, 1003, ACTION_DINNER)
        scheduleExactDaily(context, 22, 0, 1004, ACTION_LATE_SNACK_WARN)
        scheduleExactDaily(context, 21, 0, 1005, ACTION_CHECK_MISSING)
    }

    @SuppressLint("ScheduleExactAlarm")
    fun scheduleExactDaily(
        context: Context,
        hour: Int,
        minute: Int,
        requestCode: Int,
        action: String
    ) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            this.action = action
        }
        val pi = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val cal = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        am.cancel(pi)
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pi)
    }

    fun scheduleTomorrowSameTime(context: Context, requestCode: Int, action: String) {
        val (h, m) = when (action) {
            ACTION_MORNING -> 9 to 0
            ACTION_LUNCH -> 12 to 0
            ACTION_DINNER -> 18 to 0
            ACTION_LATE_SNACK_WARN -> 22 to 0
            ACTION_CHECK_MISSING -> 21 to 0
            else -> 9 to 0
        }
        scheduleExactDaily(context, h, m, requestCode, action)
    }
}

class ReminderReceiver : BroadcastReceiver() {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return

        // Always create channels before posting (idempotent)
        NotificationHelper.ensureChannels(context)

        when (action) {
            ReminderScheduler.ACTION_MORNING -> {
                NotificationHelper.showReminder(
                    context,
                    id = 3001,
                    title = "냠코치",
                    text = "잘잤어? 아침먹을 시간이네! 오늘 냠이의 첫끼는 뭐야?"
                )
                ReminderScheduler.scheduleTomorrowSameTime(context, 1001, action)
            }

            ReminderScheduler.ACTION_LUNCH -> {
                NotificationHelper.showReminder(
                    context,
                    id = 3002,
                    title = "냠코치",
                    text = "벌써 점심시간이네! 오늘의 점심은 뭐야?"
                )
                ReminderScheduler.scheduleTomorrowSameTime(context, 1002, action)
            }

            ReminderScheduler.ACTION_DINNER -> {
                NotificationHelper.showReminder(
                    context,
                    id = 3003,
                    title = "냠코치",
                    text = "오늘 하루도 곧 끝이 보이네! 냠이의 마지막 식사는 뭐야?"
                )
                ReminderScheduler.scheduleTomorrowSameTime(context, 1003, action)
            }

            ReminderScheduler.ACTION_LATE_SNACK_WARN -> {
                NotificationHelper.showReminder(
                    context,
                    id = 3004,
                    title = "냠코치",
                    text = "지금 딱 야식 땡길 시간이야.. 먹으면 안되는거 알지? 냠이랑 같이 참아보자!"
                )
                ReminderScheduler.scheduleTomorrowSameTime(context, 1004, action)
            }

            ReminderScheduler.ACTION_CHECK_MISSING -> {
                val req = OneTimeWorkRequestBuilder<TodayMissingMealsWorker>().build()
                WorkManager.getInstance(context).enqueueUniqueWork(
                    "today_missing_meals",
                    ExistingWorkPolicy.REPLACE,
                    req
                )

                ReminderScheduler.scheduleTomorrowSameTime(context, 1005, action)
            }
        }
    }
}
object NotificationHelper {
    const val REMINDER_CHANNEL_ID = "reminder_channel"

    fun ensureChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = NotificationManagerCompat.from(context)
            val reminder = NotificationChannel(
                REMINDER_CHANNEL_ID,
                "Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Daily meal record reminders"
            }
            nm.createNotificationChannel(reminder)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showReminder(
        context: Context,
        id: Int,
        title: String,
        text: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("from_alert", true)
            putExtra("deeplink", "meal_record") // handle this in MainActivity nav if desired
        }
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                id,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        val builder = NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_jam)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(id, builder.build())
    }
}

@HiltWorker
class TodayMissingMealsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val foodDao: FoodDao
) : CoroutineWorker(appContext, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        return try {
            val today = LocalDate.now() // device TZ (Asia/Seoul)
            // TODO: Replace these calls with your actual DAO queries.
            /*val hasBreakfast = foodDao.hasMealOnDate(mealType = "BREAKFAST", date = today)
            val hasLunch     = foodDao.hasMealOnDate(mealType = "LUNCH",     date = today)
            val hasDinner    = foodDao.hasMealOnDate(mealType = "DINNER",    date = today)*/
            val hasBreakfast = false
            val hasLunch = false
            val hasDinner = false
            //TODO: I have to change this logic by GET with server LATER

            val missing = mutableListOf<String>()
            if (!hasBreakfast) missing += "아침"
            if (!hasLunch)     missing += "점심"
            if (!hasDinner)    missing += "저녁"

            if (missing.isNotEmpty()) {
                NotificationHelper.ensureChannels(applicationContext)
                val text = "아직 오늘 기록이 비었어요: ${missing.joinToString(", ")}"
                NotificationHelper.showReminder(
                    applicationContext,
                    id = 3100,
                    title = "냠코치",
                    text = text
                )
            }
            Result.success()
        } catch (t: Throwable) {
            Result.retry()
        }
    }
}

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED -> {
                ReminderScheduler.ensureAllReminders(context)
            }
        }
    }
}
