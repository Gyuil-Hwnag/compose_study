package com.example.compose_study.ui.screen.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.ui.screen.feature.component.QuickLinkScreen
import com.example.compose_study.ui.screen.feature.component.TopBannerScreen
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBannerScreen()
        QuickLinkScreen(modifier = Modifier.offset(y = (-20).dp))
    }
}


@Preview
@Composable
fun FeaturePreview() {
    Compose_studyTheme {
        FeatureScreen()
    }
}