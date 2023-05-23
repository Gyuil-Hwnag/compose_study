@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun QuickLinkScreen(modifier: Modifier) {
    val hairQuickLink = QuickLink(title = "헤어샵", description = "컷/펌/염색\n스타일링\n메이크업", imageUri = R.mipmap.ic_quick_hair_foreground)
    val nailQuickLink = QuickLink(title = "네일샵", description = "케어\n네일", imageUri = R.mipmap.ic_quick_nail_foreground)
    val estheticQuickLink = QuickLink(title = "에스테틱", description = "왁싱\n브로우", imageUri = R.mipmap.ic_quick_esthetic_foreground)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        MainQuickLinkItems(quickLink = hairQuickLink, modifier = Modifier.weight(weight = 1f, fill = true))
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(weight = 1f, fill = true)) {
            SubQuickLinkItems(quickLink = nailQuickLink)
            Spacer(modifier = Modifier.size(8.dp))
            SubQuickLinkItems(quickLink = estheticQuickLink)
        }
    }
}

@Composable
fun MainQuickLinkItems(quickLink: QuickLink, modifier: Modifier) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        border = BorderStroke(10.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = {}
    ) {
        Column(
            modifier = modifier
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
        border = BorderStroke(10.dp, color = Color.White),
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
        QuickLinkScreen(modifier = Modifier)
    }
}