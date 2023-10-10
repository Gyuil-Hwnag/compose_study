package com.example.compose_study.ui.screen.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun PermissionScreen() {
    val context = LocalContext.current
    val cameraPermission = arrayOf(Manifest.permission.CAMERA)
    val galleyPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    val launcherMultiplePermissions = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("test", "권한이 동의되었습니다.")
        } else {
            Log.d("test", "권한이 거부되었습니다.")
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .background(color = Color.Blue)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable {
                        checkAndRequestPermissions(context = context, permissions = cameraPermission, launcher = launcherMultiplePermissions)
                    }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Blue),
                    text = "Camera",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp).wrapContentWidth())
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable {
                        checkAndRequestPermissions(context = context, permissions = galleyPermission, launcher = launcherMultiplePermissions)
                    }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Gallery",
                    color = Color.White
                )
            }
        }
    }
}

fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
) {

    if (permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }) {
        Log.d("test", "권한이 이미 존재합니다.")
    } else {
        launcher.launch(permissions)
        Log.d("test", "권한을 요청하였습니다.")
    }
}
