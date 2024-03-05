package com.example.compose_study.utils.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.buttonShadow(shapes: Shape): Modifier = composed {
    this.shadow(
        elevation = 16.dp,
        shape = shapes,
        spotColor = Color(0xFFAAAAAA)
    )
}
