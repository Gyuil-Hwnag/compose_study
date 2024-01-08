package com.example.compose_study.ui.screen.feature.component.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose_study.utils.ColorLineDivider
import com.example.compose_study.utils.HorizontalDivider
import com.example.compose_study.utils.LoadingCircleShape
import com.example.compose_study.utils.LoadingCornerRounded
import com.example.compose_study.utils.VerticalDivider

@Composable
fun LoadingLocationScreen(
    isLoadingCompleted: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ColorLineDivider()
        LoadingLocation(isLoadingCompleted = isLoadingCompleted)
        ColorLineDivider()
    }
}

@Composable
fun LoadingLocation(
    isLoadingCompleted: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoadingCircleShape(size = 26, isLoadingCompleted = isLoadingCompleted)
        HorizontalDivider(dp = 12)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LoadingCornerRounded(
                width = 84,
                height = 16,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
            VerticalDivider(dp = 6)
            LoadingCornerRounded(
                width = 84,
                height = 16,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
        }
        LoadingCornerRounded(
            width = 67,
            height = 29,
            cornerRounded = 4,
            isLoadingCompleted = isLoadingCompleted
        )
    }
}