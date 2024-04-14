package com.example.compose_study.utils.notification.local

data class LocalNotificationSettings(
    val pushEnable: Boolean = true,
    val hour: Int = 9,
    val minute: Int = 0,
    val second: Int = 0
)