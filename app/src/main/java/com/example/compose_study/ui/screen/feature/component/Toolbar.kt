package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        mutableStateOf(
            (if (offset() >= 200f) 1.0f else (offset() - 100f) / 100).coerceIn(0f..1f)
        )
    }
    val iconColor by remember(appBarOffset) {
        mutableStateOf(
            if (appBarOffset > 0.3f) Color.Black else Color.White
        )
    }
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White.copy(alpha = appBarOffset)
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            title = {
                Text(
                    modifier = Modifier.graphicsLayer { alpha = appBarOffset.coerceIn(0f..1f) },
                    text = "FEATURE SCREEN",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            },
            actions = {
                TopAppBarActionButton(
                    iconRes = R.drawable.ic_search,
                    description = "Search"
                ) {}
                TopAppBarActionButton(
                    iconRes = R.drawable.ic_notification,
                    description = "Lock"
                ) {}
            },
            backgroundColor = Color.Transparent,
            contentColor = iconColor,
            elevation = 0.dp,
        )
    }
}

@Composable
fun TopAppBarActionButton(
    iconRes: Int,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(painter = painterResource(id = iconRes), contentDescription = description)
    }
}

@Preview
@Composable
fun ToolBarPreview() {
    ComposeStudyTheme {
        ToolBarScreen()
    }
}
