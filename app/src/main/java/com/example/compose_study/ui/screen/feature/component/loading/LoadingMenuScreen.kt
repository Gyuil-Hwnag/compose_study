package com.example.compose_study.ui.screen.feature.component.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose_study.utils.HorizontalDivider
import com.example.compose_study.utils.LoadingCornerRounded
import com.example.compose_study.utils.VerticalDivider

@Composable
fun LoadingMenuScreen(
    isLoadingCompleted: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        LoadingCornerRounded(
            width = 240,
            height = 24,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 12)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(3) {
                LoadingCornerRounded(
                    width = 60,
                    height = 22,
                    cornerRounded = 2,
                    isLoadingCompleted = isLoadingCompleted
                )
                HorizontalDivider(dp = 16)
            }
        }
        VerticalDivider(dp = 24)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            items(count = 10) {
                LoadingMenu(isLoadingCompleted = isLoadingCompleted)
                HorizontalDivider(dp = 16)
            }
        }
    }
}

@Composable
fun LoadingMenu(
    isLoadingCompleted: Boolean
) {
    Column {
        LoadingCornerRounded(
            width = 160,
            height = 160,
            cornerRounded = 4,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 12)
        LoadingCornerRounded(
            width = 64,
            height = 16,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 4)
        LoadingCornerRounded(
            width = 130,
            height = 19,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
        VerticalDivider(dp = 4)
        LoadingCornerRounded(
            width = 90,
            height = 16,
            cornerRounded = 2,
            isLoadingCompleted = isLoadingCompleted
        )
    }
}