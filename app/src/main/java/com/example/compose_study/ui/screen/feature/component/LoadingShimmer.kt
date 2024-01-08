package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.screen.feature.component.loading.LoadingLocationScreen
import com.example.compose_study.ui.screen.feature.component.loading.LoadingMenuScreen
import com.example.compose_study.ui.screen.feature.component.loading.LoadingQuickButtonScreen
import com.example.compose_study.ui.screen.feature.component.loading.LoadingTopBannerScreen
import com.example.compose_study.utils.VerticalDivider

@Composable
fun LoadingScreen(
    isLoadingCompleted: Boolean,
    scrollState: ScrollState
) {
    if (!isLoadingCompleted) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            LoadingTopBannerScreen()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
            ) {
                LoadingQuickButtonScreen()
            }
            VerticalDivider(dp = 12)
            LoadingLocationScreen()
            VerticalDivider(dp = 40)
            LoadingMenuScreen()
            VerticalDivider(dp = 72)
        }
    }
}