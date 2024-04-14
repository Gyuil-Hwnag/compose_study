package com.example.compose_study.utils.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.util.Log
import com.example.compose_study.model.getCalendarDateTime
import java.util.Calendar
import java.util.Date

class LocalNotificationHelper(val context: Context) {

    private val alarmManager by lazy { context.applicationContext.getSystemService(ALARM_SERVICE) as? AlarmManager }

    private fun cancelNotification() {
        val receiverIntent = Intent(context.applicationContext, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context.applicationContext, 0, receiverIntent, PendingIntent.FLAG_MUTABLE)
        alarmManager?.cancel(pendingIntent)
    }

    @SuppressLint("ScheduleExactAlarm")
    fun sendNotification(pushEnable: Boolean, message: PushMessage) {
        cancelNotification()
        if (!pushEnable) return

        val receiverIntent = Intent(context.applicationContext, NotificationReceiver::class.java).apply {
            putExtra("PushMessage", message)
        }
        val pendingIntent = PendingIntent.getBroadcast(context.applicationContext, message.id.hashCode() + 1, receiverIntent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager?.cancel(pendingIntent)

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
//            set(Calendar.HOUR_OF_DAY, localAlarm.hour)
//            set(Calendar.MINUTE, localAlarm.minute)
//            set(Calendar.SECOND, localAlarm.second)
            add(Calendar.SECOND, 5)
        }
        Log.d("LocalNotificationTest", "Send To : ${calendar.time.getCalendarDateTime()}")

        if (calendar.time < Date()) {
            calendar.add(Calendar.HOUR_OF_DAY, 0)
        }

        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}