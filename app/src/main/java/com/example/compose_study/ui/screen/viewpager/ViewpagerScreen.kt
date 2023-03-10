package com.example.compose_study.ui.screen.viewpager

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewpagerScreen(
) {
    val pages = listOf<String>("Page1", "Page2", "Page3")
    val pagerState = rememberPagerState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        TabScreen(tabData = pages, pagerState = pagerState, modifier = Modifier.padding(contentPadding))
        TabContent(tabData = pages, pagerState = pagerState, modifier = Modifier.padding(contentPadding))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(tabData: List<String>, pagerState: PagerState, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier =Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = s) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabData: List<String>, pagerState: PagerState, modifier: Modifier = Modifier) {
    HorizontalPager(state = pagerState, count = tabData.size) { index ->
        when (index) {
            0 -> OneScreen()
            1 -> TwoScreen()
            2 -> ThreeScreen()
        }

    }
}

@Composable
fun OneScreen() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Text(text = "One Screen", modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(), textAlign = TextAlign.Center)
    }
}

@Composable
fun TwoScreen() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Text(text = "Two Screen", modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(), textAlign = TextAlign.Center)
    }
}

@Composable
fun ThreeScreen() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Text(text = "Three Screen", modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth()
            .wrapContentHeight(), textAlign = TextAlign.Center)
    }
}

