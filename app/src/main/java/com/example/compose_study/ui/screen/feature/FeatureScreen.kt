package com.example.compose_study.ui.screen.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.compose_study.ui.screen.feature.component.StyleBookScreen
import com.example.compose_study.ui.screen.feature.component.ToolBarScreen
import com.example.compose_study.ui.screen.feature.component.TopBannerScreen
import com.example.compose_study.ui.screen.feature.component.UpdateLocationScreen
import com.example.compose_study.ui.screen.feature.component.UpdateProfileCard
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val isExpanded = remember { derivedStateOf { scrollState.value > 200f } }

    lateinit var adapter: ConcatAdapter
    val adapterConfig = ConcatAdapter.Config.Builder()
        .setIsolateViewTypes(true)
        .setStableIdMode(ConcatAdapter.Config.StableIdMode.SHARED_STABLE_IDS)
        .build()

    adapter = ConcatAdapter(
        adapterConfig
    )

    Scaffold(
        topBar = {
            if (!isExpanded.value) ToolBarScreen(scrollState.value.toFloat())
            else ToolBarScreen()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            TopBannerScreen()
            Box(modifier = Modifier.offset(y = (-20).dp)) {
                Column {
                    QuickLinkScreen()
                    QuickCardScreen()
                }
            }
            UpdateLocationScreen()
            RecommendMenuScreen()
            ReReservationScreen()
            RecommendStyleScreen()
            EventBannerScreen()
            ReservationShopScreen()
            RecommendNailScreen()
            StyleBookScreen()
            NewShopScreen()
            UpdateProfileCard()
            RecentStyleScreen()
            BottomAndroidView(adapter = adapter)
        }
    }
}

@Preview
@Composable
fun FeaturePreview() {
    Compose_studyTheme {
        FeatureScreen()
    }
}