package com.example.compose_study.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp), Alignment.BottomCenter
    ) {
        Button(
            onClick = { onClick() }, modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(65.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, "arrow up")
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
            .height(240.dp)
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
