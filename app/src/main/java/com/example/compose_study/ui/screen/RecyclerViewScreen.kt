package com.example.compose_study.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.item.PhotoListItem
import kotlinx.coroutines.flow.Flow

@Composable
fun RecyclerViewContent(photoList: Flow<PagingData<Photo>>) {
//    val puppies = remember { DataProvider.puppyList }
//    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
//        items(items = puppies, itemContent = { PuppyListItem(it) })
//    }

    val photos = photoList.collectAsLazyPagingItems()

    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = photos,
            itemContent = {
                it?.let { PhotoListItem(photo = it) }
            }
        )
        photos.apply {
            when {
                loadState.refresh is LoadState.Loading -> Loading()
                loadState.prepend is LoadState.Loading -> Loading()
                loadState.append is LoadState.Loading -> Loading()
            }
        }
    }
}

private fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}
