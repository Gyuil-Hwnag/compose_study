package com.example.compose_study.ui.screen.foldable

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.compose_study.ui.screen.detail.DetailScreen
import com.example.compose_study.ui.screen.home.HomeScreen
import com.example.compose_study.utils.isWideDisplay
import com.google.accompanist.adaptive.FoldAwareConfiguration
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.calculateDisplayFeatures

@Composable
fun FoldableScreen(
    toDetail: (id: String) -> Unit
) {
    val activity = LocalContext.current as Activity
    val showTwoDisplay = isWideDisplay(activity = activity)

    when (showTwoDisplay) {
        true -> FoldableTwoScreen(activity = activity)
        false -> HomeScreen(toDetail = toDetail)
    }
}

@Composable
fun FoldableTwoScreen(
    activity: Activity = LocalContext.current as Activity
) {
    var detailId by remember { mutableStateOf<String?>(null) }
    TwoPane(
        modifier = Modifier.fillMaxSize(),
        first = {
            HomeScreen(
                toDetail = { detailId = if (detailId == it) null else it }
            )
        },
        second = {
            DetailScreen(
                id = detailId,
                toBack = { detailId = null }
            )
        },
        strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f),
        displayFeatures = calculateDisplayFeatures(activity),
        foldAwareConfiguration = FoldAwareConfiguration.HorizontalFoldsOnly
    )
}


