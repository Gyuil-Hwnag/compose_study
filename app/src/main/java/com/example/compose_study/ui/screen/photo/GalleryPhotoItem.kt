package com.example.compose_study.ui.screen.photo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GalleryPhotoItem(
    photoListItem: PhotoListItem,
    onSelected: () -> Unit
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(4.dp)
            .clickable { onSelected() },
        model = photoListItem.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
