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
fun QuickLinkScreen(
    quickLinks: List<QuickLink>
) {
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
                MainQuickLinkItems(quickLink = quickLinks[0])
            }
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(weight = 1f, fill = true)) {
                SubQuickLinkItems(quickLink = quickLinks[1])
                Spacer(modifier = Modifier.size(8.dp))
                SubQuickLinkItems(quickLink = quickLinks[2])
            }
        }
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(12.dp))
            }
            items(quickLinks.subList(0, 2)) { quickLink ->
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
                    modifier = Modifier.size(70.dp)
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

//@Preview
//@Composable
//fun QuickLinkPreview() {
//    Compose_studyTheme {
//        QuickLinkScreen()
//    }
//}