package com.example.compose_study.utils.notification

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.compose_study.model.getCalendarDateTime
import java.util.Date

@Suppress("DEPRECATION")
class NotificationReceiver : BroadcastReceiver() {

    private lateinit var firebaseMessagingService: FirebaseMessagingService
    private lateinit var localNotificationHelper: LocalNotificationHelper

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("LocalNotificationTest", "Received At : ${Date().getCalendarDateTime()}")
        Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show()

        firebaseMessagingService = FirebaseMessagingService(context = context)
        localNotificationHelper = LocalNotificationHelper(context = context)

        val pushMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PushMessage", PushMessage::class.java)
        } else {
            intent.getParcelableExtra<PushMessage>("PushMessage")
        }

        pushMessage?.let {
            if (it.hasContents) {
                firebaseMessagingService.sendNotification(it)
                localNotificationHelper.sendNotification(true, message = getTestPushMessage())
            }
        }
    }
}