package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.screen.feature.ContentsDivider
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StyleBookScreen() {
    val styleBook1 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/bQyU8I/btr4tNoBsRJ/l2cZnFKF006eMgY2wmwauk/img_750.jpg",
        title = "선선한 지금 날씨에 어울리는\n산뜻한 커플 헤어 스타일",
        descrption = "데이트 하기 좋은 헤어스타일"
    )
    val styleBook2 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/dYUUdr/btr4s3evFe6/zHEtmTZtFiPfa1heMkqRG1/img_750.jpg",
        title = "선선한 지금 날씨에 어울리는\n산뜻한 커플 헤어 스타일",
        descrption = "데이트 하기 좋은 헤어스타일"
    )
    val styleBook3 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/bwex7I/btsg1eOUjKe/ewCCw8X4mIB0kfepA3MKOk/img_750.jpg",
        title = "선선한 지금 날씨에 어울리는\n산뜻한 커플 헤어 스타일",
        descrption = "데이트 하기 좋은 헤어스타일"
    )
    val styleBook4 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/J5572/btseOiTG9bW/7uZc29XeCaJUR0C56Xi5m1/img_750.jpg",
        title = "선선한 지금 날씨에 어울리는\n산뜻한 커플 헤어 스타일",
        descrption = "데이트 하기 좋은 헤어스타일"
    )
    val styleBook5 = StyleBook(
        imgUri = "https://mud-kage.kakao.com/dn/c9hNrZ/btsdGQ4n6TH/ntJH2Z22k5fEkbqwi1xX50/img_750.jpg",
        title = "선선한 지금 날씨에 어울리는\n산뜻한 커플 헤어 스타일",
        descrption = "데이트 하기 좋은 헤어스타일"
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
            Card(
                Modifier.graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

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
                StyleBookItem(item = styleBooks[page])
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
fun StyleBookItem(item: StyleBook) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = item.imgUri,
                contentDescription = "스타일북 이미지",
                contentScale = ContentScale.Crop,
            )
            Row(modifier = Modifier.offset(y = (-10).dp)) {
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 20.dp),
                text = item.title,
                color = Color.Black,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = item.descrption,
                color = Color(0xFFAAAAAA),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
        }

    }
}

data class StyleBook(
    val imgUri: String,
    val title: String,
    val descrption: String
)

@Preview
@Composable
fun StyleBookPreview() {
    Compose_studyTheme {
        StyleBookScreen()
    }
}