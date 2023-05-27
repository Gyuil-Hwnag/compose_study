@file:OptIn(ExperimentalPagerApi::class)

package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
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
    val banner1 = TopBanner(imageUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToWctFQXH8UDrKh45hZk7toE2r-Yocs7ys6g&usqp=CAU", title = "헤어 스타일의 모든것\nBOBBED HAIR STYLE", description = "요즘 유행하는 스타일")
    val banner2 = TopBanner(imageUri = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg", title = "네일 스타일의 모든것\nBOBBED NAIL STYLE", description = "요즘 유행하는 네일 스타일")
    val banner3 = TopBanner(imageUri = "https://mblogthumb-phinf.pstatic.net/MjAxODA2MjZfMTMw/MDAxNTMwMDE0NDk2Mzcz.7re42MA5wqJxZlJ8J5FzfDKEEqugtVuhg49bSFYUuYsg.0Y0kjwH4oi1LXXpqrcGaVBch_4eQsyKyVTRsNtg7fCMg.JPEG.ichufs/%EC%82%AC%EC%A7%84%EC%8C%A4%EC%9A%B0%EC%93%B0%EB%9D%BC_3_0%EC%9D%B8%ED%8A%B8%EB%A1%9C.jpg?type=w800", title = "에스테틱의 모든것\nBOBBED ESTHETIC STYLE", description = "요즘 유행하는 에스테틱 스타일")

    val banners = listOf<TopBanner>(banner3, banner1, banner2, banner3, banner1, banner2, banner3, banner1)
    val state = rememberPagerState()

    LaunchedEffect(key1 = Unit) {
        state.scrollToPage(1)
    }

    LaunchedEffect(key1 = state.currentPage) {
        when(state.currentPage) {
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

    TopBannerSlider(state, banners)
}

@Composable
fun TopBannerSlider(state: PagerState, banners: List<TopBanner>) {
    Box(contentAlignment = Alignment.BottomStart) {
        HorizontalPager(
            state = state,
            count = banners.size, modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            TopBannerItem(banner = banners[page])
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = banners[state.currentPage].title,
                color = Color.White,
                fontSize = 22.sp
            )

            Row {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 20.dp),
                    text = banners[state.currentPage].description,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                BannerIndicator(current = state.currentPage, totalCount = banners.size - 2)
            }
            Spacer(modifier = Modifier.size(44.dp))
        }
    }
}

@Composable
fun TopBannerItem(banner: TopBanner) {
    AsyncImage(
        model = banner.imageUri,
        contentDescription = "배너 이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(1f)
    )
}

@Composable
fun BannerIndicator(current: Int, totalCount: Int) {
    Surface(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(11.dp),
        color = Color(0x4D111111)
    ) {
        Text(modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp), text = "$current / $totalCount", fontSize = 11.sp, color = Color.White)
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