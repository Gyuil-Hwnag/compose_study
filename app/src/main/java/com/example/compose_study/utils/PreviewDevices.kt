package com.example.compose_study.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name= "Phone", showBackground = true, device = Devices.PHONE)
@Preview(name= "Fold", showBackground = true, device = Devices.PIXEL_FOLD)
@Preview(name= "Tablet", showBackground = true, device = Devices.TABLET)
annotation class PreviewDevices

@PreviewDevices
@Composable
fun DevicesPreviews() {
    MaterialTheme {
        EmptyScreen()
    }
}

@Composable
fun EmptyScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
        content = {}
    )
}