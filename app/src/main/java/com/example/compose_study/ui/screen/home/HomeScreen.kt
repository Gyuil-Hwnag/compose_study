package com.example.compose_study.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.ui.ScrollToTopButton
import com.example.compose_study.ui.screen.PhotoLazyColumn
import com.example.compose_study.ui.screen.TextFieldItem

@Composable
fun HomeScreen(
    toDetail: (id: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(8f)) {
                PhotoLazyColumn(photoList = viewModel.photos, onClick = { toDetail(it) })
            }
            TextFieldItem(viewModel)
        }

        ScrollToTopButton(onClick = { viewModel.scrollToTop() }, modifier = Modifier.padding(contentPadding))
    }
}
