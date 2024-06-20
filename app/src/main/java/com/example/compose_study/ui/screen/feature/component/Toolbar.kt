package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme

@Composable
fun ToolBarScreen(
    offset: () -> Float = { 0f }
) {
    val currentOffset = offset()
    val appBarOffset by remember(currentOffset) {
        mutableFloatStateOf(
            (if (offset() >= 200f) 1.0f else (offset() - 100f) / 100).coerceIn(0f..1f)
        )
    }
    val iconColor by remember(appBarOffset) {
        mutableStateOf(
            if (appBarOffset > 0.3f) Color.Black else Color.White
        )
    }
    val textAlpha by remember(appBarOffset) {
        mutableFloatStateOf(
            appBarOffset.coerceIn(0f..1f)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White.copy(alpha = appBarOffset))
            .statusBarsPadding()
            .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.graphicsLayer { alpha = textAlpha },
            text = "FEATURE SCREEN",
            color = Color.Black,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        TopAppBarActionButton(
            iconRes = R.drawable.ic_search,
            description = "Search",
            color = iconColor
        )
        TopAppBarActionButton(
            iconRes = R.drawable.ic_notification,
            description = "Lock",
            color = iconColor
        )
    }
}

@Composable
fun TopAppBarActionButton(
    iconRes: Int,
    description: String,
    color: Color,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = description,
            tint = color
        )
    }
}

@Preview
@Composable
fun ToolBarPreview() {
    ComposeStudyTheme {
        ToolBarScreen()
    }
}
