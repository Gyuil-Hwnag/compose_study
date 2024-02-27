package com.example.compose_study.utils.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.compose_study.R

@Stable
@Composable
fun AsyncGif(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale
) {
    val context = LocalContext.current
    AsyncImage(
        modifier = modifier,
        contentScale = contentScale,
        imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                else add(GifDecoder.Factory())
            }
            .placeholder(R.drawable.shape_solid_n8_rectangle)
            .error(R.drawable.shape_solid_n8_rectangle)
            .build(),
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .placeholder(R.drawable.shape_solid_n8_rectangle)
            .error(R.drawable.shape_solid_n8_rectangle)
            .build(),
        contentDescription = "이미지"
    )
}

@Stable
@Composable
fun AsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale
) {
    AsyncImage(
        modifier = modifier,
        contentScale = contentScale,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.shape_solid_n8_rectangle)
            .error(R.drawable.shape_solid_n8_rectangle)
            .build(),
        contentDescription = "이미지"
    )
}