package com.example.compose_study.ui.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_study.ui.model.Puppy
import com.example.compose_study.ui.theme.Typography

@Composable
fun PuppyListItem(puppy: Puppy) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            PuppyImage(puppy = puppy)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = puppy.name, style = Typography.h6)
                Text(text = puppy.content, style = Typography.caption, modifier = Modifier.padding(0.dp,2.dp))
            }
        }
    }
}

@Composable
fun PuppyImage(puppy: Puppy) {
    AsyncImage(
        model = puppy.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )

}
