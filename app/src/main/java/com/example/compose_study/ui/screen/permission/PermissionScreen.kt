package com.example.compose_study.ui.screen.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
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
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage

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
        if (areGranted) {
            toPhotoPicker()
        } else {
            openAppSettings(context = context)
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
        }
    }
}

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

fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    context.startActivity(intent)
}




