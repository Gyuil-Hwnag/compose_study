package com.example.compose_study.ui.screen.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.recyclerview.widget.ConcatAdapter
import com.example.compose_study.ui.screen.feature.component.BottomAndroidView
import com.example.compose_study.ui.screen.feature.component.EventBannerScreen
import com.example.compose_study.ui.screen.feature.component.NewShopScreen
import com.example.compose_study.ui.screen.feature.component.QuickCardScreen
import com.example.compose_study.ui.screen.feature.component.QuickLinkScreen
import com.example.compose_study.ui.screen.feature.component.ReReservationScreen
import com.example.compose_study.ui.screen.feature.component.RecentStyleScreen
import com.example.compose_study.ui.screen.feature.component.RecommendMenuScreen
import com.example.compose_study.ui.screen.feature.component.RecommendNailScreen
import com.example.compose_study.ui.screen.feature.component.RecommendStyleScreen
import com.example.compose_study.ui.screen.feature.component.ReservationShopScreen
import com.example.compose_study.ui.screen.feature.component.TodayStyleBookScreen
import com.example.compose_study.ui.screen.feature.component.ToolBarScreen
import com.example.compose_study.ui.screen.feature.component.TopBannerScreen
import com.example.compose_study.ui.screen.feature.component.UpdateLocationScreen
import com.example.compose_study.ui.screen.feature.component.UpdateProfileCard
import com.example.compose_study.ui.screen.feature.component.loading.LoadingScreen
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val adapterConfig = ConcatAdapter.Config.Builder()
        .setIsolateViewTypes(true)
        .setStableIdMode(ConcatAdapter.Config.StableIdMode.SHARED_STABLE_IDS)
        .build()

    val adapter: ConcatAdapter by lazy { ConcatAdapter(adapterConfig) }

    val banners by viewModel.banners.collectAsState()
    val quickLinks by viewModel.quickLinks.collectAsState()
    val quickCards by viewModel.quickCards.collectAsState()
    val isLoadingCompleted by viewModel.isLoadingCompleted.collectAsState()
    val tabs by viewModel.tabs.collectAsState()
    val selectedTab by viewModel.selectedTabs.collectAsState()

    LaunchedEffect(key1 = isLoadingCompleted) {
        if (isLoadingCompleted) scrollState.scrollTo(value = 0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolBarScreen(offset = { scrollState.value.toFloat() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            if (banners.isNotEmpty()) {
                TopBannerScreen(banners)
            }

            Box(modifier = Modifier.offset(y = (-20).dp)) {
                Column {
                    if (quickLinks.size >= 3) QuickLinkScreen(quickLinks)
                    if (quickCards.isNotEmpty()) QuickCardScreen(quickCards)
                }
            }
            UpdateLocationScreen()
            RecommendMenuScreen {
                // Tab 관련 테스트를 위한 클릭
                viewModel.loadReReservationTabs()
            }
            ReReservationScreen(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabClicked = { viewModel.onSelectedTab(it) }
            )
            RecommendStyleScreen()
            EventBannerScreen()
            ReservationShopScreen()
            RecommendNailScreen()
            TodayStyleBookScreen()
            NewShopScreen()
            UpdateProfileCard()
            RecentStyleScreen()
            BottomAndroidView(adapter = adapter)
        }
        LoadingScreen(isLoadingCompleted = isLoadingCompleted, scrollState = scrollState)
    }
}

@Preview
@Composable
fun FeaturePreview() {
    ComposeStudyTheme {
        FeatureScreen()
    }
}
