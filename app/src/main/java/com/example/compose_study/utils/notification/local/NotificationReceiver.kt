package com.example.compose_study.utils.notification.local

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import com.example.compose_study.utils.notification.FirebaseMessagingService
import com.example.compose_study.utils.notification.PushMessage
import com.example.compose_study.utils.notification.getExactPushMessage
import com.example.compose_study.utils.notification.getNonExactPushMessage
import com.example.compose_study.utils.notification.getPushMessage

@Suppress("DEPRECATION")
class NotificationReceiver : BroadcastReceiver() {

    private lateinit var firebaseMessagingService: FirebaseMessagingService
    private lateinit var localNotificationHelper: LocalNotificationHelper

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "주기 알림 수신", Toast.LENGTH_SHORT).show()

        firebaseMessagingService = FirebaseMessagingService(context = context)
        localNotificationHelper = LocalNotificationHelper(context = context)

        val message = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PushMessage", PushMessage::class.java)
        } else {
            intent.getParcelableExtra<PushMessage>("PushMessage")
        }

        message?.let {
            if (it.hasContents) {
                firebaseMessagingService.sendNotification(it)

                when (it.timeMode) {
                    PushMessage.TimeMode.EXACT -> localNotificationHelper.sendNotification(message = getExactPushMessage())
                    PushMessage.TimeMode.NON_EXACT -> localNotificationHelper.sendNotification(message = getNonExactPushMessage())
                }
            }
        }
    }
}