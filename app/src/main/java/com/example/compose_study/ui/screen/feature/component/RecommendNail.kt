package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme

@Composable
fun RecommendNailScreen() {
    val style1 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOSZ7uu3jRt1HS2vLqvqllS8YKk1Iin6bzKQ&usqp=CAU",
        tag = listOf("#C컬펌", "#레이어드컷")
    )
    val style2 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkgnszpJPNhKJ2cRNM_iOSWZjGgA4fTi1HFoQaBcI1uv641trTcFWNoO8FAjsnsuA8kRs&usqp=CAU",
        tag = listOf("#C컬펌")
    )
    val style3 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-uac15nGLt8beRpjyNH8OzZrplm1joHwtVwJeO0gpAG83rUvsTth18XClSBJqkX5sQPk&usqp=CAU",
        tag = listOf("#C컬펌")
    )
    val style4 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJPNJQJrpXAzRIVwoxg-EB4Vc0PJfLwp6UDg&usqp=CAU",
        tag = listOf("#C컬펌", "#레이어드컷")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        RecommendNailTitle()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendNailItem(item = style1)
                }
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendNailItem(item = style2)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendNailItem(item = style3)
                }
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendNailItem(item = style4)
                }
            }
        }
        RecommendNailMore()
        ContentsDivider()
    }
}

@Composable
fun RecommendNailMore() {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            onClick = {},
            border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "네일 스타일 모두보기",
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "모두 보기 버튼")
            }

        }
    }
}

@Composable
fun RecommendNailTitle() {
    Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), text = "시에나님을 위한 네일 스타일", fontSize = 20.sp)
}

@Composable
fun RecommendNailItem(item: RecommendStyle) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = item.imgUri,
                contentDescription = "메뉴 이미지",
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item.tag.forEach {
                StyleTag(item = it)
            }
        }
    }
}

@Preview
@Composable
fun RecommendNailPreview() {
    ComposeStudyTheme {
        RecommendNailScreen()
    }
}
