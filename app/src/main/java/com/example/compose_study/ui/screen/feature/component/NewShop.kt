package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.ui.screen.feature.ContentsDivider
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun NewShopScreen() {
    val reservation = NewShop(
        shopImg = "http://www.04one.co.kr/data/goodsImages/GOODS1_160577396320201127155905.jpg",
        shopName = "오슈아헤어 강남논현점",
        shopDescription = "역삼역 3번출구 도보 5분, 스타타워뒷길"
    )

    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        NewShopTitle()
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
            contentPadding = PaddingValues(start = 10.dp)
        ) {
            items(listOf(reservation, reservation, reservation)) {
                NewShopItem(item = it)
            }
        }
        ContentsDivider()
    }
}

@Composable
fun NewShopTitle() {
    Text(modifier = Modifier.padding(horizontal = 16.dp), text = "새로 오픈한 헤어샵", fontSize = 20.sp)
}

@Composable
fun NewShopItem(item: NewShop) {
    Column(
        modifier = Modifier
            .width(331.dp)
            .padding(6.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                model = item.shopImg,
                contentDescription = "매장 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.49f),
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = item.shopName, color = Color.Black, fontSize = 16.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = item.shopDescription, color = Color(0xFF888888), fontSize = 12.sp)
    }
}

data class NewShop(
    val shopImg: String,
    val shopName: String,
    val shopDescription: String
)

@Preview
@Composable
fun NewShopPreview() {
    Compose_studyTheme {
        NewShopScreen()
    }
}