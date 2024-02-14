package com.example.compose_study.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.Empty
import com.example.compose_study.ui.ImageBigSize
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.screen.PhotoLazyGrid

@Composable
fun DetailScreen(
    id: String? = null,
    toBack: () -> Unit = {},
    viewModel: DetailViewModel = hiltViewModel()
) {
    if (!id.isNullOrBlank()) {
        viewModel.getPhoto(id)
    }
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
        if (id == null) {
            Empty()
            viewModel.getEmptyView()
        }
        id?.let {
            if (photo == null) Loading()
        }
        photo?.let {
            PhotoDetail(paddingValues = paddingValues, photo = photo!!)
        }
    }
}

@Composable
fun PhotoDetail(
    paddingValues: PaddingValues,
    photo: Photo,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ImageBigSize(url = photo.url)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            text = photo.author
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            text = photo.download_url
        )
        PhotoLazyGrid(
            photoList = viewModel.photos,
            onClick = { viewModel.getPhoto(id = it) })
    }
}
