package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun QuickLinkScreen() {
    val hairQuickLink = QuickLink(title = "헤어샵", description = "컷/펌/염색\n스타일링\n메이크업", imageUri = R.mipmap.ic_quick_hair_foreground)
    val nailQuickLink = QuickLink(title = "네일샵", description = "케어\n네일", imageUri = R.mipmap.ic_quick_nail_foreground)
    val estheticQuickLink = QuickLink(title = "에스테틱", description = "왁싱\n브로우", imageUri = R.mipmap.ic_quick_esthetic_foreground)

    val subLink1 = QuickLink(title = "\uD83C\uDFE0 첫방문 기획전", description = "")
    val subLink2 = QuickLink(title = "\uD83C\uDFC6 어워즈", description = "")
    val subLink3 = QuickLink(title = "⭐️ 리뷰별점높은샵", description = "")
    val subLink4 = QuickLink(title = "\uD83D\uDC87 스타일TIP", description = "")
    val subQuickLinks = listOf<QuickLink>(subLink1, subLink2, subLink3, subLink4, subLink1, subLink2, subLink3, subLink4)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                MainQuickLinkItems(quickLink = hairQuickLink)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(weight = 1f, fill = true)) {
                SubQuickLinkItems(quickLink = nailQuickLink)
                Spacer(modifier = Modifier.size(8.dp))
                SubQuickLinkItems(quickLink = estheticQuickLink)
            }
        }
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(12.dp))
            }
            items(subQuickLinks) { quickLink ->
                QuickLinkItems(quickLink)
            }
            item {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }

}

@Composable
fun MainQuickLinkItems(quickLink: QuickLink) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Text(text = quickLink.title, fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(6.dp))
            Text(text = quickLink.description, fontSize = 12.sp, color = Color(0xFFAAAAAA))

            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = quickLink.imageUri,
                    contentDescription = "배너 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(77.dp)
                )
            }
        }
    }
}

@Composable
fun SubQuickLinkItems(quickLink: QuickLink) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {}
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(text = quickLink.title, fontSize = 18.sp, color = Color.Black, modifier = Modifier)
                Spacer(modifier = Modifier.size(6.dp))
                Text(text = quickLink.description, fontSize = 12.sp, color = Color(0xFFAAAAAA))
            }
            Spacer(modifier = Modifier.weight(1f))
            AsyncImage(
                model = quickLink.imageUri,
                contentDescription = "배너 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(44.dp)
            )
        }
    }
}

@Composable
fun QuickLinkItems(quickLink: QuickLink) {
    Button(
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {}
    ) {
        Row {
            if (quickLink.imageUri != 0) {
                AsyncImage(
                    model = quickLink.imageUri,
                    contentDescription = "배너 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
            Text(text = quickLink.title, fontSize = 13.sp, color = Color.Black)
        }
    }
}

data class QuickLink(
    val title: String,
    val description: String,
    val imageUri: Int = 0
)

@Preview
@Composable
fun QuickLinkPreview() {
    Compose_studyTheme {
        QuickLinkScreen()
    }
}