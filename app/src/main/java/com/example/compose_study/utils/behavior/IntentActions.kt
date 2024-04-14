package com.example.compose_study.utils.behavior

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity

fun checkInstagramAppLink(
    context: Context,
    url: String
): Boolean {
    val uri = Uri.parse(url)
    val isInstagramUri = uri.host?.endsWith("instagram.com") == true
    val hasInstagramApp: Boolean = context.packageManager?.getLaunchIntentForPackage("com.instagram.android") != null

    if (isInstagramUri && hasInstagramApp) {
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.instagram.android")
        }
        context.startActivity(intent)
        return true
    }
    return false
}

fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    context.startActivity(intent)
}

fun scheduleNotificationSettings(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        startActivity(context, Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM),null)
    } else {
        openAppSettings(context = context)
    }
}
