package com.example.compose_study.ui.screen.viewpagerwithtabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.screen.ConstraintScreen
import com.example.compose_study.ui.screen.more.More
import com.example.compose_study.ui.screen.more.MoreScreen
import com.example.compose_study.ui.screen.todo.TodoScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

enum class Designer(val value: String) {
    Reservation("예약"),
    Style("스타일"),
    Review("리뷰"),
    More("더보기")
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerWithTabBarScreen(
    onMoreClicked: (more: More) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            val pages = listOf(
                Designer.Reservation.value,
                Designer.Style.value,
                Designer.Review.value,
                Designer.More.value
            )

            val pagerState = rememberPagerState()
            val indicator = @Composable { tabPositions: List<TabPosition> ->
                TabIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .width(tabPositions[pagerState.currentPage].width),
                    color = Color.White,
                )
            }
            val coroutineScope = rememberCoroutineScope()

            // TAB
            TabRow(
                modifier = Modifier.wrapContentWidth(),
                selectedTabIndex = pagerState.currentPage,
                indicator = indicator
            ) {
                pages.forEachIndexed { index, title ->
                    Tab(
                        modifier = Modifier.background(Color.Black),
                        text = { Text(text = title, color = Color.White) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    )
                }
            }

            // PAGER
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                userScrollEnabled = false

            ) { index ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(pages[index]) {
                        Designer.Reservation.value -> TodoScreen()
                        Designer.More.value -> MoreScreen(onClick = { onMoreClicked.invoke(it) })
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun TabIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface
) {
    Spacer(
        modifier = modifier
            .height(2.dp)
            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
    )
}


