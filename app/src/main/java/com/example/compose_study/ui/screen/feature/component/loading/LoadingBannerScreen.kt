package com.example.compose_study.ui.screen.feature.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_study.utils.ui.LoadingCornerRounded
import com.example.compose_study.utils.ui.LoadingFillMaxWidth
import com.example.compose_study.utils.ui.VerticalDivider

@Composable
fun LoadingTopBannerScreen(
    isLoadingCompleted: Boolean = false
) {
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        LoadingFillMaxWidth(
            ratio = 1f,
            color = Color(0xFFDDDDDD),
            isLoadingCompleted = isLoadingCompleted
        )
        LoadingTopBannerTitles(isLoadingCompleted = isLoadingCompleted)
    }
}

@Composable
fun LoadingTopBannerTitles(
    isLoadingCompleted: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        LoadingCornerRounded(
            width = 217,
            height = 24,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 4)
        LoadingCornerRounded(
            width = 217,
            height = 24,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 8)
        LoadingCornerRounded(
            width = 123,
            height = 19,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 52)
    }
}