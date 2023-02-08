package com.example.compose_study.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.ScrollToTopButton
import com.example.compose_study.ui.item.GridPhotoItem
import com.example.compose_study.ui.item.PhotoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun PhotoLazyColumn(
    photoList: Flow<PagingData<Photo>>, onClick: (id: String) -> Unit
) {
    val photos = photoList.collectAsLazyPagingItems()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember{ derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    if (photos.loadState.refresh is LoadState.Loading) Loading()

    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = photos,
            itemContent = {
                it?.let {
                    PhotoItem(photo = it, onClick = { onClick(it) })
                }
            }
        )
    }

    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ScrollToTopButton(onClick = {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        })
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

