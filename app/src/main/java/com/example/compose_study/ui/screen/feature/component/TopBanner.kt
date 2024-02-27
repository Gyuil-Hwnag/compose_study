package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.example.compose_study.utils.endOffsetForPage
import com.example.compose_study.utils.offsetForPage
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

/**
 * Scroll Delay 관련 : https://github.com/google/accompanist/issues/1261
 * accompanist-pager version > 0.25.01 : https://stackoverflow.com/questions/73714228/accompanist-pager-animatescrolltopage-doesnt-scroll-to-next-page-correctly
 * pagerState.currentPage 관련 에러
 **/

@Composable
fun TopBannerScreen(
    banners: List<TopBanner>
) {
    TopBannerSlider(banners)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TopBannerSlider(banners: List<TopBanner>) {
    val pagerState = rememberPagerState(initialPage = banners.infiniteLoopInitPage(), pageCount = { banners.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var nextPage by remember { mutableStateOf(banners.infiniteLoopInitPage()) }
    var offsetY by remember { mutableStateOf(0f) }
    var pageSize by remember { mutableStateOf(IntSize.Zero) }

    if (!isDragged) {
        LaunchedEffect(key1 = nextPage) {
            delay(3000)
            tween<Float>(durationMillis = 1400)
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(durationMillis = 1400)
            )
            nextPage = pagerState.currentPage + 1
        }
    }

    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInteropFilter {
                    offsetY = it.y
                    false
                }.onSizeChanged { pageSize = it },
            state = pagerState,
        ) { page ->
            val pageOffset = pagerState.offsetForPage(page)
            val endOffset = pagerState.endOffsetForPage(page)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationX = size.width * pageOffset
                        shape = RectPath(
                            progress = 1f - endOffset.absoluteValue,
                            origin = Offset(size.width, size.height)
                        )
                        clip = true
                    },
                contentAlignment = Alignment.BottomStart
            ) {
                TopBannerItem(banner = banners[page % banners.size], pageOffset = pageOffset)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                BannerIndicator(current = (pagerState.currentPage % banners.size) + 1, totalCount = banners.size)
            }
            Spacer(modifier = Modifier.size(44.dp))
        }
    }
}

@Composable
fun TopBannerItem(banner: TopBanner, pageOffset: Float) {
    val fastParallax = (2 * pageOffset)
    val defaultParallax = (pageOffset)
    AsyncImage(
        model = banner.imageUri,
        contentDescription = "배너 이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(1f)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .graphicsLayer {
                    translationX = - size.width * fastParallax
                },
            text = banner.title,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp)
                .graphicsLayer {
                    translationX = - size.width * defaultParallax
                },
            text = banner.description,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.size(44.dp))
    }
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
        },
        label = ""
    ) { text ->
        content(text)
    }
}

class RectPath(private val progress: Float, private val origin: Offset = Offset(0f, 0f)): Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {

        val startOffset = Offset(
            x = (size.center.x - (size.center.x - origin.x)) * (1f - progress),
            y = 0f
        )

        val boxSize = Size(
            width = (size.width * size.width * .5f) * progress,
            height = origin.y
        )
        return Outline.Generic(Path().apply {
            addRect(
                Rect(
                    offset = startOffset,
                    size = boxSize
                )
            )
        })
    }
}

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
