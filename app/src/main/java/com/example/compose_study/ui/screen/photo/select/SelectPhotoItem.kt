package com.example.compose_study.ui.screen.photo.select

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SelectPhotoItem(
    photo: SelectPhoto,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(4.dp)
                .clickable { onSelected() },
            model = photo.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Checkbox(
            checked = photo.isChecked,
            onCheckedChange = { photo.onPhotoChecked(!photo.isChecked) }
        )
    }

}
