package com.example.compose_study.utils.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingFillMaxWidth(
    ratio: Float,
    color: Color = Color(0xFFEEEEEE),
    isLoadingCompleted: Boolean
) {
    Box(
        modifier = Modifier
            .background(color = color)
            .fillMaxWidth()
            .aspectRatio(ratio)
            .shimmerLoadingAnimation(isLoadingCompleted)
    )
}

@Composable
fun LoadingCornerRounded(
    width: Int,
    height: Int,
    cornerRounded: Int,
    color: Color = Color(0xFFEEEEEE),
    isLoadingCompleted: Boolean
) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(size = cornerRounded.dp))
            .width(width.dp)
            .height(height.dp)
            .shimmerLoadingAnimation(isLoadingCompleted)
    )
}

@Composable
fun LoadingCircleShape(
    size: Int,
    color: Color = Color(0xFFEEEEEE),
    isLoadingCompleted: Boolean
) {
    Box(
        modifier = Modifier
            .background(color = color, shape = CircleShape)
            .size(size.dp)
            .shimmerLoadingAnimation(isLoadingCompleted)
    )
}