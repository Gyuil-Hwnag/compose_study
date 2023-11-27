package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

/**
 * Scroll Delay 관련 : https://github.com/google/accompanist/issues/1261
 * accompanist-pager version > 0.25.01 : https://stackoverflow.com/questions/73714228/accompanist-pager-animatescrolltopage-doesnt-scroll-to-next-page-correctly
 * pagerState.currentPage 관련 에러
 **/
@ExperimentalPagerApi
@Composable
fun TopBannerScreen(
    banners: List<TopBanner>
) {
    TopBannerSlider(banners)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBannerSlider(banners: List<TopBanner>) {
    val pagerState = rememberPagerState(initialPage = banners.infiniteLoopInitPage())
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var nextPage by remember { mutableStateOf(banners.infiniteLoopInitPage()) }
    var pageSize by remember { mutableStateOf(IntSize.Zero) }

    if (!isDragged) {
        LaunchedEffect(key1 = pagerState.currentPage) {
            delay(3000)
            tween<Float>(durationMillis = 1400)
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1
            )
            nextPage = pagerState.currentPage + 1
        }
    }

    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        HorizontalPager(
            state = pagerState,
            count = Int.MAX_VALUE,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            TopBannerItem(
                modifier = Modifier.onSizeChanged { pageSize = it },
                banner = banners[page % banners.size]
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            BannerAnimatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                targetText = banners[pagerState.currentPage % banners.size].title,
                content = {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 22.sp
                    )
                }
            )


            Row {
                BannerAnimatedText(
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp),
                    targetText = banners[pagerState.currentPage % banners.size].description,
                    content = {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                BannerIndicator(current = (pagerState.currentPage % banners.size) + 1, totalCount = banners.size)
            }
            Spacer(modifier = Modifier.size(44.dp))
        }
    }
}

@Composable
fun TopBannerItem(modifier: Modifier, banner: TopBanner) {
    AsyncImage(
        model = banner.imageUri,
        contentDescription = "배너 이미지",
        contentScale = ContentScale.Crop,
        modifier = modifier
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BannerAnimatedText(
    modifier: Modifier,
    content: @Composable (text: String) -> Unit,
    targetText: String
) {
    AnimatedContent(
        modifier = modifier,
        targetState = targetText,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = slideInHorizontally { width -> width } + fadeIn(animationSpec = tween(durationMillis = BANNER_TITLE_ANIMATED_TIME)),
                initialContentExit = fadeOut(animationSpec = tween(durationMillis = 0))
            )
        }
    ) { text ->
        content(text)
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun TopBannerPreview() {
    ComposeStudyTheme {
        TopBannerScreen(emptyList())
    }
}

data class TopBanner(
    val imageUri: String,
    val title: String,
    val description: String
)

const val BANNER_TITLE_ANIMATED_TIME = 1500
