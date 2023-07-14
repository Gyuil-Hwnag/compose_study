package com.example.compose_study.ui.screen.feature.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.example.compose_study.R

@Stable
@Composable
fun AsyncImageCrop(
    modifier: Modifier,
    imageUrl: String
) {
    coil.compose.AsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.shape_solid_n8_rectangle)
            .error(R.drawable.shape_solid_n8_rectangle)
            .build(),
        contentDescription = "이미지"
    )
}

@Stable
@Composable
fun AsyncImageFillBounds(
    modifier: Modifier,
    imageUrl: String
) {
    coil.compose.AsyncImage(
        modifier = modifier,
        contentScale = ContentScale.FillBounds,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.shape_solid_n8_rectangle)
            .error(R.drawable.shape_solid_n8_rectangle)
            .build(),
        contentDescription = "이미지"
    )
}