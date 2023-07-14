package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

fun List<Any>.infiniteLoopInitPage(): Int {
    val size = this.size
    return ((Int.MAX_VALUE / size)/2) * size
}


@Composable
fun ContentsDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    )
}