package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
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
import com.example.compose_study.ui.screen.feature.data.StyleBook
import com.example.compose_study.ui.screen.feature.data.styleBooks
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.example.compose_study.utils.offsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodayStyleBookScreen() {
    val pagerState = rememberPagerState(initialPage = styleBooks.infiniteLoopInitPage(), pageCount = { Int.MAX_VALUE })


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TodayStyleBookTitle()
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 40.dp, start = 40.dp)
        ) { page ->
            val pageOffset = pagerState.offsetForPage(page = page).absoluteValue
            val alphaOffset = (1.3f) * pagerState.offsetForPage(page = page).absoluteValue
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
                TodayStyleBookItem(item = styleBooks[page % styleBooks.size], pageOffset = pageOffset, alphaOffset = (1f - alphaOffset.coerceIn(0f, 1f)))
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().size(24.dp))
        Indicator(
            totalDots = styleBooks.size,
            selectedIndex = pagerState.currentPage % styleBooks.size
        )
        ContentsDivider()
    }
}

@Composable
fun TodayStyleBookTitle() {
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
fun TodayStyleBookItem(item: StyleBook, pageOffset: Float, alphaOffset: Float) {
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

@Preview
@Composable
fun StyleBookPreview() {
    ComposeStudyTheme {
        TodayStyleBookScreen()
    }
}
