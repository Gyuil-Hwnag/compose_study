package com.example.compose_study.ui.screen.draw

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TriangleEdgeShape(val offset: Int) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(x = 24f, y = size.height)
            lineTo(x = 36f, y = size.height + offset)
            lineTo(x = 48f, y = size.height)
        }
        return Outline.Generic(path = trianglePath)
    }
}
