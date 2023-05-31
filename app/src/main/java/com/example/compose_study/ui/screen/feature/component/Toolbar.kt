package com.example.compose_study.ui.screen.feature.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun ToolBarScreen(offset: Float = 200f) {
    val offsetNum = offset - 100f
    TopAppBar(
        title = {
            Text(text = "kakaohairshop", color = Color.Black, fontSize = 14.sp, modifier = Modifier.alpha((offsetNum/100).coerceIn(0f..1f)))
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
        backgroundColor = Color.White.copy(alpha = (offsetNum/100).coerceIn(0f..1f)),
        contentColor = Color.Transparent,
        elevation = 0.dp
    )
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
    Compose_studyTheme {
        ToolBarScreen()
    }
}