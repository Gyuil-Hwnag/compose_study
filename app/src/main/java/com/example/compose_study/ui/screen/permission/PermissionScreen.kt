package com.example.compose_study.ui.screen.permission

import android.app.AlarmManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import coil.compose.AsyncImage
import com.example.compose_study.utils.behavior.checkAndRequestGalleryPermissions
import com.example.compose_study.utils.behavior.checkInstagramAppLink
import com.example.compose_study.utils.behavior.openAppSettings
import com.example.compose_study.utils.behavior.scheduleNotificationSettings
import com.example.compose_study.utils.permission.galleyPermission
import com.example.compose_study.utils.notification.FirebaseMessagingService
import com.example.compose_study.utils.notification.getExactPushMessage
import com.example.compose_study.utils.notification.getNonExactPushMessage
import com.example.compose_study.utils.notification.local.LocalNotificationHelper
import com.example.compose_study.utils.notification.getPushMessage
import com.example.compose_study.utils.permission.notificationPermission

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PermissionScreen(
    toPhotoPicker: () -> Unit,
    selectedPhoto: String = "",
    toSelectPhoto: () -> Unit,
    selectedPhotos: List<String> = emptyList()
) {
    val context = LocalContext.current
    val alarmManager = context.getSystemService<AlarmManager>()
    val firebaseMessagingService = FirebaseMessagingService(context = context)
    val localNotificationHelper = LocalNotificationHelper(context = context)

    val photoMultiplePermissionsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) toPhotoPicker() else openAppSettings(context = context)
    }

    val notificationPermissionsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) {
            Toast.makeText(context, "알림 권한을 허용 하였습니다.", Toast.LENGTH_SHORT).show()
            firebaseMessagingService.sendNotification(message = getPushMessage())
        } else {
            Toast.makeText(context, "알림 권한을 차단 하였습니다.", Toast.LENGTH_SHORT).show()
            openAppSettings(context = context)
        }
    }

    val exactRepeatNotificationPermissionsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        when {
            areGranted && alarmManager?.canScheduleExactAlarms() == true -> {
                Toast.makeText(context, "정확한 시간 알림 권한을 허용 하였습니다.", Toast.LENGTH_SHORT).show()
                localNotificationHelper.sendNotification(message = getExactPushMessage())
            }
            areGranted && alarmManager?.canScheduleExactAlarms() == false -> {
                Toast.makeText(context, "정확한 시간 알림 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
                scheduleNotificationSettings(context)
            }
            else -> {
                Toast.makeText(context, "알림 권한을 차단 하였습니다.", Toast.LENGTH_SHORT).show()
                openAppSettings(context = context)
            }
        }
    }

    val nonExactRepeatNotificationPermissionsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) {
            Toast.makeText(context, "알림 권한을 허용 하였습니다.", Toast.LENGTH_SHORT).show()
            firebaseMessagingService.sendNotification(message = getNonExactPushMessage())
        } else {
            Toast.makeText(context, "알림 권한을 차단 하였습니다.", Toast.LENGTH_SHORT).show()
            openAppSettings(context = context)
        }
    }

    val scrollState = rememberScrollState()
    val selectPhotoMultiplePermissionsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) toSelectPhoto() else openAppSettings(context = context)
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
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { checkInstagramAppLink(context = context, url = "https://www.instagram.com/duru_hgi/") }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Instagram 프로필",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { checkInstagramAppLink(context = context, url = "https://www.instagram.com/p/CfhDRMLPeHZ/?utm_source=ig_web_copy_link&igshid=MzRlODBiNWFlZA==") }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Instagram 피드",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable {
                        checkAndRequestGalleryPermissions(
                            context = context,
                            permissions = galleyPermission,
                            launcher = photoMultiplePermissionsLauncher,
                            toPermissionGranted = toPhotoPicker
                        )
                    }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Gallery",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp).wrapContentWidth())
            if (selectedPhoto.isNotBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .size(256.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    model = selectedPhoto,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { notificationPermissionsLauncher.launch(notificationPermission) }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Notification",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp).wrapContentWidth())
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { exactRepeatNotificationPermissionsLauncher.launch(notificationPermission) }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Repeat Notification (Exact)",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp).wrapContentWidth())
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable { nonExactRepeatNotificationPermissionsLauncher.launch(notificationPermission) }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Repeat Notification (Non Exact)",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
            Surface(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .clickable {
                        checkAndRequestGalleryPermissions(
                            context = context,
                            permissions = galleyPermission,
                            launcher = selectPhotoMultiplePermissionsLauncher,
                            toPermissionGranted = toSelectPhoto
                        )
                    }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Gallery Multiple",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp).wrapContentWidth())
            if (selectedPhotos.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    selectedPhotos.forEach {
                        AsyncImage(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            model = it,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }

                }

            }
        }
    }
}



