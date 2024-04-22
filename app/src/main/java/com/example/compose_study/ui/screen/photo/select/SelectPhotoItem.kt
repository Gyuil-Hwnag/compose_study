package com.example.compose_study.ui.screen.photo.select

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SelectPhotoItem(
    photo: SelectPhoto,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
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
                .clickable { onCheckedChange(!photo.isChecked) },
            model = photo.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
    }

}
