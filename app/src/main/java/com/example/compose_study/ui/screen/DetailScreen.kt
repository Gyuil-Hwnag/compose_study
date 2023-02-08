package com.example.compose_study.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.Loading

@Composable
fun DetailScreen(
    id: String,
    toBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.getPhoto(id = id)
    val photo = viewModel.photoState.collectAsState().value

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
        if(photo == null) {
            Loading()
        } else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

                PhotoImageBigSize(photo = photo)
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    text = photo.author
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    text = photo.download_url
                )
            }
        }
    }
}

@Composable
fun PhotoImageBigSize(photo: Photo) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(photo.url)
        .placeholder(com.example.compose_study.R.drawable.ic_launcher_foreground)
        .size(Size(128, 128))
        .build()

    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )
}
