package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
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
    val styleBooks = listOf(styleBook4, styleBook5, styleBook1, styleBook2, styleBook3, styleBook4, styleBook5, styleBook1, styleBook2)

    val pagerState = rememberPagerState(initialPage = 2)

    LaunchedEffect(key1 = pagerState.currentPage) {
        when (pagerState.currentPage) {
            styleBooks.size - 2 -> { pagerState.scrollToPage(2) }
            1 -> { pagerState.scrollToPage(styleBooks.size - 3) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        StyleBookTitle()
        HorizontalPager(
            state = pagerState,
            count = styleBooks.size,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 40.dp, start = 40.dp)
        ) { page ->
            // Calculate the absolute offset for the current page from the
            // scroll position. We use the absolute value which allows us to mirror
            // any effects for both directions
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            val alphaOffset = (1.3f) * calculateCurrentOffsetForPage(page).absoluteValue
            Card(
                modifier = Modifier.graphicsLayer {
                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    },
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            ) {
                StyleBookItem(item = styleBooks[page], pageOffset = pageOffset, alphaOffset = (1f - alphaOffset.coerceIn(0f, 1f)))
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(24.dp)
        )
        Indicator(
            totalDots = styleBooks.size - 4,
            selectedIndex = when (pagerState.currentPage) {
                styleBooks.size - 2 -> 0
                1 -> styleBooks.size - 2
                else -> pagerState.currentPage - 2
            }
        )
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
        Text(text = "오늘의 스타일북", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right_25),
            contentDescription = "더보기 이미지"
        )
    }
}

@Composable
fun StyleBookItem(item: StyleBook, pageOffset: Float, alphaOffset: Float) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = item.imgUri,
                contentDescription = "스타일북 이미지",
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier.offset(y = (-10).dp)
            ) {
                Spacer(modifier = Modifier.size(18.dp))
                Surface(
                    color = Color.Black,
                    shape = RoundedCornerShape(2.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                        text = "#스타일추천",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
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
                    color = Color.Black.copy(alpha = alphaOffset),
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = item.description,
                    color = Color(0xFFAAAAAA).copy(alpha = alphaOffset),
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.size(24.dp))
        }

    }
}

data class StyleBook(
    val imgUri: String,
    val title: String,
    val description: String
)

@Preview
@Composable
fun StyleBookPreview() {
    ComposeStudyTheme {
        StyleBookScreen()
    }
}
