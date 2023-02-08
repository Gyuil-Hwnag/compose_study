package com.example.compose_study.ui.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.ImageSmallSize
import com.example.compose_study.ui.theme.Typography

@Composable
fun GridPhotoItem(photo: Photo, onClick: (id: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 8.dp)
            .clickable { onClick(photo.id) },
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageSmallSize(url = photo.url)
            Text(text = photo.author, style = Typography.h6, maxLines = 1, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = photo.download_url, style = Typography.caption, modifier = Modifier.padding(0.dp,2.dp), maxLines = 1)
        }
    }
}
