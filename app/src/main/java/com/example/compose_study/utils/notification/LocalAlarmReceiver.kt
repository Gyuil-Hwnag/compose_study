package com.example.compose_study.utils.notification

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast

@Suppress("DEPRECATION")
class LocalAlarmReceiver : BroadcastReceiver() {

    private lateinit var firebaseMessagingService: FirebaseMessagingService

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show()
        firebaseMessagingService = FirebaseMessagingService(context = context)
        val pushMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PushMessage", PushMessage::class.java)
        } else {
            intent.getParcelableExtra<PushMessage>("PushMessage")
        }

        pushMessage?.let {
            if (it.hasContents) firebaseMessagingService.sendNotification(it)
        }
    }
}