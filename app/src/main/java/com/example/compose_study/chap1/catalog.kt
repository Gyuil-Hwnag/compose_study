package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun CatalogScreen() {
    val items = listOf(
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg","코코", "코코코코코 코 코코코 입!"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "보리","보리 보리 쌀"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "초코","초코송이 먹고싶당"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "콩이", "나는 콩을 안좋아하지만 콩이는 좋아!"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "사랑이","사랑이 덕분에 집에 사랑이 가득~"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "달이", "달 달 무슨 달 쟁반같이 둥근 달"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "별이", "별.. 별.. 별.."),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "해피","암 쏘 해피~~~~ "),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "마루","마루야.. 가지마.."),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "까미", "난 영어 이름도 있어. Kami"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg","코코", "코코코코코 코 코코코 입!"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "보리","보리 보리 쌀"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "초코","초코송이 먹고싶당"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "콩이", "나는 콩을 안좋아하지만 콩이는 좋아!"),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "사랑이","사랑이 덕분에 집에 사랑이 가득~"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "달이", "달 달 무슨 달 쟁반같이 둥근 달"),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "별이", "별.. 별.. 별.."),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "해피","암 쏘 해피~~~~ "),
        ItemData( "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "마루","마루야.. 가지마.."),
        ItemData("https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "까미", "난 영어 이름도 있어. Kami")
    )
    CatalogExample(items)
}

@Composable
fun CatalogExample(itemList: List<ItemData>) {
    LazyColumn {
        items(itemList) { item ->
            Item(itemData = item)
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(elevation = CardDefaults.cardElevation(), modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = itemData.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = itemData.title, style = androidx.compose.material.MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = itemData.description)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogPreview() {
    Compose_studyTheme {
        CatalogScreen()
    }
}

data class ItemData(
    val image: String,
    val title: String,
    val description: String
)
