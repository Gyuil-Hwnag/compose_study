package com.example.compose_study.utils.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

val galleyPermission =
    when {
        (Build.VERSION.SDK_INT >= 34) -> arrayOf(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED, Manifest.permission.READ_MEDIA_IMAGES)
        (Build.VERSION.SDK_INT >= 33) -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
val notificationPermission = arrayOf(Manifest.permission.POST_NOTIFICATIONS)