package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.utils.offsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StyleBookScreen() {
    val styleBook1 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/L9UdN/btsAJH5wAeB/EtTetfFHsKohELIwNsMlk1/img_750.jpg",
        title = "요즘 헤어 추구미\n일상에서도 유니크하게",
        description = "#처피뱅 #해쉬컷 #발레야쥬"
    )
    val styleBook2 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/b7Z4VW/btsz6gGx68s/SOpIKTYerfjzdMCbg52Rvk/img_750.jpg",
        title = "요즘 헤어 추구미\n러블리 & 페미닌 무드 스타일",
        description = "#히피펌 #숏컷 #리프컷"
    )
    val styleBook3 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/1paLT/btsyFoGNEqm/m7kIsrwvVCg7qHmKkVeLm1/img_750.jpg",
        title = "남자들의 워너비 헤어\n장발 추천 스타일 모음",
        description = "장발로 기를 때 하기 좋은 머리"
    )
    val styleBook4 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/9iUwt/btsA20Q4Jq1/KjS7h8paKvYvBnTqFXMIkK/img_750.jpg",
        title = "비슷해 보여도 달라요!\n알고 쓰면 좋은 홈케어 사용법",
        description = "트린트먼트 vs 린스, 뭐가 다를까?"
    )
    val styleBook5 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/b7t8zw/btsAmsOpz89/O7tu3eQUNLEkTyl0bQroIK/img_750.jpg",
        title = "나에게 딱 맞는\n가을 헤어 컬러는?",
        description = "피부 톤 찰떡 염색 컬러 추천"
    )
    val styleBooks = listOf(styleBook1, styleBook2, styleBook3, styleBook4, styleBook5)
    val pagerState = rememberPagerState(initialPage = styleBooks.infiniteLoopInitPage())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        StyleBookTitle()
        HorizontalPager(
            state = pagerState,
            pageCount = Int.MAX_VALUE,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            val pageOffset = pagerState.offsetForPage(page = page).absoluteValue
            val alphaOffset = (1.3f) * pagerState.offsetForPage(page = page).absoluteValue
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            ) {
                StyleBookItem(item = styleBooks[page % styleBooks.size], pageOffset = pageOffset, alphaOffset = (1f - alphaOffset.coerceIn(0f, 1f)))
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().size(24.dp))
        ContentsDivider()
    }
}

@Composable
fun StyleBookTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "스타일북", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun StyleBookItem(item: StyleBook, pageOffset: Float, alphaOffset: Float) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        coil.compose.AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = item.imgUri,
            contentDescription = "스타일북 이미지",
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = size.height * pageOffset
                }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 20.dp),
                text = item.title,
                color = Color.White.copy(alpha = alphaOffset),
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = item.description,
                color = Color.White.copy(alpha = alphaOffset),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}
