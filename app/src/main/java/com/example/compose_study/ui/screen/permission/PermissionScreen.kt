package com.example.compose_study.ui.screen.permission

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import coil.compose.AsyncImage
import com.example.compose_study.utils.behavior.checkAndRequestPermissions
import com.example.compose_study.utils.behavior.checkInstagramAppLink
import com.example.compose_study.utils.behavior.openAppSettings
import com.example.compose_study.utils.notification.FirebaseMessagingService
import com.example.compose_study.utils.notification.PushMessage
import com.example.compose_study.utils.notification.testPushMessage

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PermissionScreen(
    toPhotoPicker: () -> Unit,
    selectedPhoto: String = ""
) {
    val context = LocalContext.current
    val galleyPermission =
        when {
            (Build.VERSION.SDK_INT >= 34) -> arrayOf(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED, Manifest.permission.READ_MEDIA_IMAGES)
            (Build.VERSION.SDK_INT >= 33) -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc || next }
        if (areGranted) toPhotoPicker() else openAppSettings(context = context)
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
                        checkAndRequestPermissions(
                            context = context,
                            permissions = galleyPermission,
                            launcher = launcherMultiplePermissions,
                            toPhotoPicker = toPhotoPicker
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
                    .clickable {
                        FirebaseMessagingService().sendNotification(testPushMessage)
                    }
            ) {
                Text(
                    modifier = Modifier.background(color = Color.Black),
                    text = "Notification",
                    color = Color.White
                )
            }
        }
    }
}



