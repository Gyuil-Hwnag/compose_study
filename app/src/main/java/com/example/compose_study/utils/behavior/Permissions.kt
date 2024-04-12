package com.example.compose_study.utils.behavior

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    toPhotoPicker: () -> Unit,
) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) -> {
            Toast.makeText(context, "사진 전체 허용 하였습니다.", Toast.LENGTH_SHORT).show()
            toPhotoPicker()
        }

        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) -> {
            Toast.makeText(context, "사진 일부 허용 하였습니다.", Toast.LENGTH_SHORT).show()
            launcher.launch(permissions)
        }

        else -> {
            Toast.makeText(context, "권한을 요청 하였습니다.", Toast.LENGTH_SHORT).show()
            launcher.launch(permissions)
        }
    }
}