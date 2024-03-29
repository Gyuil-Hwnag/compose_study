package com.example.compose_study.ui.screen.stylebook

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.utils.ui.Category
import com.example.compose_study.ui.screen.feature.component.RoundedTabs
import com.example.compose_study.ui.screen.feature.data.styleBooks

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
    val tabs = listOf<Category>(
        Category("ALL"),
        Category("여성"),
        Category("남성"),
        Category("스페셜"),
        Category("바버샵"),
        Category("네일샵"),
        Category("오놀의 스타일"),
        Category("봄 스타일 추천"),
        Category("여름 스타일 추천"),
        Category("가을 스타일 추천"),
        Category("겨울 스타일 추천"),
    )
    var selectIndex by remember { mutableStateOf(0) }
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
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
        ) {
            StyleBannerScreen()
            RoundedTabs(
                tabs = tabs,
                selectedTabIndex = selectIndex,
                onTabClicked = { tabIndex, _ -> selectIndex = tabIndex }
            )

            repeat(styleBooks.size) { index ->
                StyleBookItem(item = styleBooks[index])
            }
        }
    }
}



