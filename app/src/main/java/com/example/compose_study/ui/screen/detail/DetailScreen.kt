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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.ui.ImageBigSize
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.screen.PhotoLazyGrid

@Composable
fun DetailScreen(
    id: String,
    toBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.getPhoto(id = id)
    val photo = viewModel.photoState.collectAsState()

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
        if(photo.value == null) {
            Loading()
        } else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

                ImageBigSize(url = photo.value!!.url)
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    text = photo.value!!.author
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    text = photo.value!!.download_url
                )

                PhotoLazyGrid(photoList = viewModel.photos, onClick = { viewModel.getPhoto(id = it) })
            }
        }
    }
}
