package com.example.compose_study.utils.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.util.Log
import java.util.Calendar
import java.util.Date

class LocalAlarmHelper(val context: Context) {

    private val alarmManager by lazy { context.applicationContext.getSystemService(ALARM_SERVICE) as? AlarmManager }

    fun cancelNotification() {
        val receiverIntent = Intent(context.applicationContext, LocalAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context.applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager?.cancel(pendingIntent)
    }

    fun sendNotification(localAlarm: LocalAlarm, pushMessage: PushMessage) {
        if (!localAlarm.pushEnable) return

        val receiverIntent = Intent(context.applicationContext, LocalAlarmReceiver::class.java).apply {
            putExtra("PushMessage", pushMessage)
        }
        val pendingIntent = PendingIntent.getBroadcast(context.applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager?.cancel(pendingIntent)

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, localAlarm.hour)
            set(Calendar.MINUTE, localAlarm.minute)
            set(Calendar.SECOND, localAlarm.second)
        }

        if (calendar.time < Date()) {
            calendar.add(Calendar.HOUR_OF_DAY, 0)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )
    }
}