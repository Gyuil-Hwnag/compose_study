package com.example.compose_study.utils.notification

data class NotificationsTime(
    val pushEnable: Boolean,
    val hour: Int,
    val minute: Int,
    val second: Int
)