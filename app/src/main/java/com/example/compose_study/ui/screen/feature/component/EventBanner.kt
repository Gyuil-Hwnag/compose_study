package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventBannerScreen() {
    val banner1 = EventBanner(
        bannerUri = "https://blog.imqa.io/content/images/2022/08/IMQALITE_------------_-----------.jpg"
    )
    val banner2 = EventBanner(
        bannerUri = "https://png.pngtree.com/thumb_back/fh260/background/20201020/pngtree-minimal-black-friday-event-banner-with-brown-ornament-image_427189.jpg"
    )
    val banner3 = EventBanner(
        bannerUri = "https://png.pngtree.com/thumb_back/fh260/background/20201019/pngtree-abstract-black-friday-event-banner-with-colorful-ornament-image_424224.jpg"
    )
    val banners = listOf(banner1, banner2, banner3)

    val pagerState = rememberPagerState(initialPage = banners.infiniteLoopInitPage())
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var nextPage by remember { mutableStateOf(banners.infiniteLoopInitPage()) }
    var pageSize by remember { mutableStateOf(IntSize.Zero) }

    if (!isDragged) {
        LaunchedEffect(key1 = nextPage) {
            delay(3000)
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(durationMillis = 1400)
            )
            nextPage = pagerState.currentPage + 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        EventBannerTitle()
        HorizontalPager(
            state = pagerState,
            pageCount = Int.MAX_VALUE,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 12.dp, start = 12.dp)
        ) { page ->
            EventBannerItem(
                modifier = Modifier.onSizeChanged { pageSize = it },
                item = banners[page % banners.size]
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(24.dp)
        )
        Indicator(
            totalDots = banners.size,
            selectedIndex = pagerState.currentPage % banners.size
        )
        ContentsDivider()
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
fun EventBannerItem(modifier: Modifier, item: EventBanner) {
    Surface(
        modifier = modifier
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
    ComposeStudyTheme {
        EventBannerScreen()
    }
}
