@file:OptIn(ExperimentalPagerApi::class)

package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun TopBannerScreen() {
    val banner = TopBanner(imageUri = "https://www.howtodoandroid.com/images/coco.jpg", title = "헤어 스타일의 모든것\nBOBBED HAIR STYLE", description = "요즘 유행하는 스타일")
    val banners = listOf<TopBanner>(banner, banner, banner, banner, banner, banner)
    val state = rememberPagerState()
    LaunchedEffect(key1 = state.currentPage) {
        delay(3000)
        var newPosition = state.currentPage + 1
        if (newPosition > banners.size - 1) newPosition = 0
        // scrolling to the new position.
        state.animateScrollToPage(newPosition)
    }

    TopBannerSlider(state, banners)
}

@Composable
fun TopBannerSlider(state: PagerState, banners: List<TopBanner>) {
    HorizontalPager(
        state = state,
        count = banners.size, modifier = Modifier
            .fillMaxWidth()
    ) { page ->
        TopBannerItem(banner = banners[page])
    }
}

@Composable
fun TopBannerItem(banner: TopBanner) {
    Box(contentAlignment = Alignment.BottomStart) {
        AsyncImage(
            model = banner.imageUri,
            contentDescription = "배너 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .aspectRatio(1f)
        )
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = banner.title,
                color = Color.White,
                fontSize = 22.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 20.dp),
                text = banner.description,
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(44.dp))
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun TopBannerPreview() {
    Compose_studyTheme {
        TopBannerScreen()
    }
}

data class TopBanner(
    val imageUri: String,
    val title: String,
    val description: String
)