package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun ProfileCardScreen() {
    ProfileCardExample()
}

@Composable
fun ProfileCardExample() {
    val cardData = CardData(
        imageUri = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg",
        imageDescription = "여행 사진",
        author = "~~~~",
        description = "Photo Picker"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
    }
}

@Composable
fun CardExample(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Column {
        Card(modifier = Modifier.fillMaxWidth().padding(4.dp), elevation = CardDefaults.cardElevation()) {
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop,
                    model = cardData.imageUri,
                    contentDescription = cardData.imageDescription
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Text(text = cardData.description)
                }

            }
        }

        Card(modifier = Modifier.fillMaxWidth().padding(4.dp), elevation = CardDefaults.cardElevation()) {
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(placeHolderColor),
                    model = cardData.imageUri,
                    contentDescription = cardData.imageDescription
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Text(text = cardData.description)
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ComposeStudyTheme {
        ProfileCardScreen()
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)
