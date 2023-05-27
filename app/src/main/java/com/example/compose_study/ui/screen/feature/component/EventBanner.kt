@file:OptIn(ExperimentalPagerApi::class)

package com.example.compose_study.ui.screen.feature.component

import android.media.metrics.Event
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventBannerScreen() {
    val state = rememberPagerState()
    val banner1 = EventBanner(
        bannerUri = "https://blog.imqa.io/content/images/2022/08/IMQALITE_------------_-----------.jpg"
    )
    val banner2 = EventBanner(
        bannerUri = "https://png.pngtree.com/thumb_back/fh260/background/20201020/pngtree-minimal-black-friday-event-banner-with-brown-ornament-image_427189.jpg"
    )
    val banner3 = EventBanner(
        bannerUri = "https://png.pngtree.com/thumb_back/fh260/background/20201019/pngtree-abstract-black-friday-event-banner-with-colorful-ornament-image_424224.jpg"
    )
    val banners = listOf(banner3, banner1, banner2, banner3, banner1)

    LaunchedEffect(key1 = Unit) {
        state.scrollToPage(1)
    }

    LaunchedEffect(key1 = state.currentPage) {
        when (state.currentPage) {
            banners.size - 1 -> {
                state.scrollToPage(1)
                return@LaunchedEffect
            }

            0 -> {
                state.scrollToPage(banners.size - 2)
                return@LaunchedEffect
            }
        }
        delay(3000)
        var newPosition = state.currentPage + 1
        if (newPosition > banners.size - 1) newPosition = 0
        // scrolling to the new position.
        state.animateScrollToPage(newPosition)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        EventBannerTitle()
        HorizontalPager(
            state = state,
            count = banners.size,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 12.dp, start = 12.dp)
        ) { page ->
            EventBannerItem(item = banners[page])
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(24.dp)
        )
        Indicator(
            totalDots = banners.size - 2,
            selectedIndex = when (state.currentPage) {
                banners.size - 1 -> 0
                0 -> banners.size - 2
                else -> state.currentPage - 1
            }
        )
    }
}

@Composable
fun EventBannerTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "놓칠 수 없는 혜택", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.ic_arrow_right_25), contentDescription = "더보기 이미지")
    }
}

@Composable
fun EventBannerItem(item: EventBanner) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            model = item.bannerUri,
            contentDescription = "메뉴 이미지",
            contentScale = ContentScale.Crop,
        )
    }
}

data class EventBanner(
    val bannerUri: String
)

@Preview
@Composable
fun EventBannerPreview() {
    Compose_studyTheme {
        EventBannerScreen()
    }
}