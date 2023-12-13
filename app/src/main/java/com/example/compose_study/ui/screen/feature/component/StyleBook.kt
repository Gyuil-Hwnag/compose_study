package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.ui.screen.feature.data.StyleBook
import com.example.compose_study.ui.screen.feature.data.styleBooks
import com.example.compose_study.utils.offsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StyleBookScreen() {
    val pagerState = rememberPagerState(initialPage = styleBooks.infiniteLoopInitPage())
    var pageOffset by remember { mutableStateOf(0f) }
    val alphaOffset by remember { derivedStateOf { (1.3f) * pageOffset } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        StyleBookTitle()
        HorizontalPager(
            state = pagerState,
            pageCount = Int.MAX_VALUE,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            pageOffset = pagerState.offsetForPage(page = page).absoluteValue
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            ) {
                StyleBookItem(item = styleBooks[page % styleBooks.size], pageOffset = pageOffset, alphaOffset = (1f - alphaOffset.coerceIn(0f, 1f)))
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().size(12.dp))
        StyleBookIndicator(pageOffset = pagerState.currentPageOffsetFraction, currentPage = (pagerState.currentPage % styleBooks.size) + 1, pageSize = styleBooks.size)
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
                color = White.copy(alpha = alphaOffset),
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = item.description,
                color = White.copy(alpha = alphaOffset),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun StyleBookIndicator(pageOffset: Float, currentPage: Int, pageSize: Int) {
    val animatedProgress = animateFloatAsState(
        targetValue = (pageOffset + currentPage) / pageSize,
        label = ""
    ).value

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .padding(horizontal = 16.dp),
        color = Black,
        backgroundColor = LightGray,
        progress = animatedProgress,
    )
}
