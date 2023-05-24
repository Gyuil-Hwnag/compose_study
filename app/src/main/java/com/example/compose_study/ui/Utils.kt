package com.example.compose_study.ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White.copy(0.1f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 90.dp, end = 10.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.Blue,
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = "Scroll To Top FAB",
                tint = Color.White,
            )
        }
    }
}

@Composable
fun ImageBigSize(url: String) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .placeholder(com.example.compose_study.R.drawable.ic_launcher_foreground)
        .size(Size(128, 128))
        .build()

    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )
}

@Composable
fun ImageSmallSize(url: String) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .placeholder(com.example.compose_study.R.drawable.ic_launcher_foreground)
        .size(Size(128, 128))
        .build()

    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )

}

@Composable
fun IconUtils(url: Drawable) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .placeholder(com.example.compose_study.R.drawable.ic_launcher_foreground)
        .size(Size(128, 128))
        .build()

    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(28.dp)
    )
}
