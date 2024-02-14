package com.example.compose_study.ui.screen.foldable

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.compose_study.ui.screen.detail.DetailScreen
import com.example.compose_study.ui.screen.home.HomeScreen
import com.google.accompanist.adaptive.FoldAwareConfiguration
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.calculateDisplayFeatures

@Composable
fun FoldableScreen() {
    val activity = LocalContext.current as Activity
    TwoPane(
        modifier = Modifier.fillMaxSize(),
        first = {
            HomeScreen(
                toDetail = {}
            )
        },
        second = {
            DetailScreen()
        },
        strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f),
        displayFeatures = calculateDisplayFeatures(activity),
        foldAwareConfiguration = FoldAwareConfiguration.HorizontalFoldsOnly
    )
}
