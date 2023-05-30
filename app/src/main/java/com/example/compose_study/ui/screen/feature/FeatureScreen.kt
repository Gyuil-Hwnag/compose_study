package com.example.compose_study.ui.screen.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.R
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
import com.example.compose_study.ui.screen.feature.component.StyleBookScreen
import com.example.compose_study.ui.screen.feature.component.ToolBarScreen
import com.example.compose_study.ui.screen.feature.component.TopAppBarActionButton
import com.example.compose_study.ui.screen.feature.component.TopBannerScreen
import com.example.compose_study.ui.screen.feature.component.UpdateLocationScreen
import com.example.compose_study.ui.screen.feature.component.UpdateProfileCard
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {},
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {
            TopBannerScreen()
            Box(modifier = Modifier.offset(y = (-20).dp)) {
                Column {
                    QuickLinkScreen()
                    QuickCardScreen()
                }
            }
            UpdateLocationScreen()
            ContentsDivider()
            RecommendMenuScreen()
            ContentsDivider()
            ReReservationScreen()
            ContentsDivider()
            RecommendStyleScreen()
            ContentsDivider()
            EventBannerScreen()
            ContentsDivider()
            ReservationShopScreen()
            ContentsDivider()
            RecommendNailScreen()
            ContentsDivider()
            StyleBookScreen()
            ContentsDivider()
            NewShopScreen()
            ContentsDivider()
            UpdateProfileCard()
            ContentsDivider()
            RecentStyleScreen()
            ContentsDivider()
        }
        if (scrollState.value > 0) ToolBarScreen()
    }
}

@Composable
fun ContentsDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    )
}


@Preview
@Composable
fun FeaturePreview() {
    Compose_studyTheme {
        FeatureScreen()
    }
}