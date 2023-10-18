package com.example.compose_study.chap2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun ConstraintsCardScreen() {
    ConstraintsCardExample()
}

@Composable
fun ConstraintsCardExample() {
    val cardData = CardData(
        imageUri = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg",
        imageDescription = "여행 사진",
        author = "~~~~",
        description = "Photo PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto PickerPhoto Picker"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
        CardExample(cardData = cardData)
    }
}

@Composable
fun CardExample(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (profileImage, author, description) = createRefs()

            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .constrainAs(profileImage) {
                        linkTo(parent.top, parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
//                        centerVerticallyTo(parent)
                    },
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(placeHolderColor),
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription
            )

            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
//                    start.linkTo(profileImage.end, margin = 16.dp)
                    linkTo(profileImage.end, parent.end, startMargin = 16.dp, endMargin = 16.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(profileImage.end, parent.end, startMargin = 16.dp, endMargin = 16.dp)
                    top.linkTo(author.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )

            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintsCardPreview() {
    ComposeStudyTheme {
        ConstraintsCardScreen()
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)
