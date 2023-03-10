package com.example.compose_study.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.item.GridPhotoItem
import com.example.compose_study.ui.item.PhotoItem
import com.example.compose_study.ui.screen.home.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PhotoLazyColumn(
    photoList: Flow<PagingData<Photo>>, onClick: (id: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val photos = photoList.collectAsLazyPagingItems()

    val scrollState = rememberLazyListState()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.scrollToTopEvent.collectLatest {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }
            }
        }
    }

    if (photos.loadState.refresh is LoadState.Loading) Loading()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp, 8.dp),
        state = scrollState
    ) {
        items(
            items = photos,
            itemContent = {
                it?.let {
                    PhotoItem(photo = it, onClick = { onClick(it) })
                }
            }
        )
    }

    if(photos.loadState.append is LoadState.Loading) Loading()
}

@Composable
fun PhotoLazyGrid(
    photoList: Flow<PagingData<Photo>>, onClick: (id: String) -> Unit
) {
    val photos = photoList.collectAsLazyPagingItems()

    if (photos.loadState.refresh is LoadState.Loading) Loading()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp, 8.dp),
    ) {
        items(
            count = photos.itemCount
        ) { index ->
            photos[index]?.let { photo ->
                GridPhotoItem(photo = photo, onClick = { onClick(it) })
            }
        }

    }

    if(photos.loadState.append is LoadState.Loading) Loading()
}

