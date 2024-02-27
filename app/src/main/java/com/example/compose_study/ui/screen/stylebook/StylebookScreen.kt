package com.example.compose_study.ui.screen.stylebook

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StyleBookScreen(
    viewModel: StylebookViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val headerOffset by remember {
        derivedStateOf {
            if (scrollState.value > 100) 100f else scrollState.value.toFloat()
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            StyleBookHeader(
                offset = headerOffset,
                title = "스타일북",
                onSearchClicked = {},
                onPhotoClicked = {}
            )
        }
    ) {
        StyleBannerScreen()
    }
}



