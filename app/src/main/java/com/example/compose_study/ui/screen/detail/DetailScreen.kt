package com.example.compose_study.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.ui.ImageBigSize
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.screen.PhotoLazyGrid

@Composable
fun DetailScreen(
    id: String? = null,
    toBack: () -> Unit = {},
    viewModel: DetailViewModel = hiltViewModel()
) {
    id?.let { viewModel.getPhoto(id = it) }
    val photo by viewModel.photoState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { toBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { paddingValues ->
        if (photo == null) Loading()

        photo?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ImageBigSize(url = it.url)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp),
                    text = it.author
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp),
                    text = it.download_url
                )
                PhotoLazyGrid(
                    photoList = viewModel.photos,
                    onClick = { viewModel.getPhoto(id = it) })
            }
        }
    }
}
