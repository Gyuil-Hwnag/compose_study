package com.example.compose_study.ui.screen.vibrate

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun VibrateScreen(
    viewModel: VibrateViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val vibratorManager = activity.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    val vibrator = vibratorManager.defaultVibrator
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.vibrate.collectLatest {
                    val (milliseconds: Long, amplitude: Int) = it
                    vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, amplitude))
                }
            }
        }
    }
}

@Composable
fun VibrateButton(
    milliseconds: Long,
    amplitude: Int,
    onClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .background(color = Color.Black)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable { onClicked() }
    ) {
        Text(
            modifier = Modifier.background(color = Color.Black),
            text = "Vibrate \n milliseconds: $milliseconds \n amplitude: $amplitude",
            color = Color.White
        )
    }
    Spacer(modifier = Modifier.height(12.dp).wrapContentWidth())
}


