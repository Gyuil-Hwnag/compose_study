package com.example.compose_study.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.item.PhotoListItem
import kotlinx.coroutines.flow.Flow

@Composable
fun PhotoLazyColumn(
    photoList: Flow<PagingData<Photo>>, onClick: (id: String) -> Unit
) {
    val photos = photoList.collectAsLazyPagingItems()

    if (photos.loadState.refresh is LoadState.Loading) Loading()

    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = photos,
            itemContent = {
                it?.let {
                    PhotoListItem(photo = it, onClick = { onClick(it) })
                }
            }
        )
    }

    if(photos.loadState.append is LoadState.Loading) Loading()
}


