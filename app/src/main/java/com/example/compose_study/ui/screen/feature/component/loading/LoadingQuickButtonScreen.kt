package com.example.compose_study.ui.screen.feature.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_study.utils.HorizontalDivider
import com.example.compose_study.utils.LoadingCircleShape
import com.example.compose_study.utils.LoadingCornerRounded
import com.example.compose_study.utils.VerticalDivider


@Composable
fun LoadingQuickButtonScreen(
    isLoadingCompleted: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    LoadingMainShopQuickButtons(isLoadingCompleted = isLoadingCompleted)
                }
                HorizontalDivider(dp = 12)
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    LoadingSubShopQuickButtons(isLoadingCompleted = isLoadingCompleted)
                    VerticalDivider(dp = 8)
                    LoadingSubShopQuickButtons(isLoadingCompleted = isLoadingCompleted)
                }
            }
        }
        VerticalDivider(dp = 16)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            item { HorizontalDivider(dp = 16) }
            items(count = 10) {
                LoadingQuickButton(isLoadingCompleted = isLoadingCompleted)
                HorizontalDivider(dp = 12)
            }
            item { HorizontalDivider(dp = 16) }
        }
    }
}

@Composable
fun LoadingMainShopQuickButtons(
    isLoadingCompleted: Boolean
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(164.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {},
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 16.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LoadingCornerRounded(
                width = 80,
                height = 22,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
            VerticalDivider(dp = 4)
            LoadingCornerRounded(
                width = 50,
                height = 14,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
            VerticalDivider(dp = 2)
            LoadingCornerRounded(
                width = 50,
                height = 14,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
            VerticalDivider(dp = 2)
            LoadingCornerRounded(
                width = 50,
                height = 14,
                cornerRounded = 2,
                isLoadingCompleted = isLoadingCompleted
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                LoadingCircleShape(size = 70, isLoadingCompleted = isLoadingCompleted)
            }
        }
    }
}

@Composable
fun LoadingSubShopQuickButtons(
    isLoadingCompleted: Boolean
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {},
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 16.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                LoadingCornerRounded(
                    width = 80,
                    height = 22,
                    cornerRounded = 2,
                    isLoadingCompleted = isLoadingCompleted
                )
                VerticalDivider(dp = 4)
                LoadingCornerRounded(
                    width = 50,
                    height = 14,
                    cornerRounded = 2,
                    isLoadingCompleted = isLoadingCompleted
                )
                VerticalDivider(dp = 2)
                LoadingCornerRounded(
                    width = 50,
                    height = 14,
                    cornerRounded = 2,
                    isLoadingCompleted = isLoadingCompleted
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.BottomEnd
            ) {
                LoadingCircleShape(size = 44, isLoadingCompleted = isLoadingCompleted)
            }
        }
    }
}

@Composable
fun LoadingQuickButton(
    isLoadingCompleted: Boolean
) {
    Button(
        modifier = Modifier
            .width(89.dp)
            .height(40.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {},
        contentPadding = PaddingValues(all = 12.dp),
    ) {
        LoadingCornerRounded(width = 65, height = 16, cornerRounded = 2, isLoadingCompleted = isLoadingCompleted)
    }
}