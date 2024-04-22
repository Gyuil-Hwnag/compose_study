package com.example.compose_study.ui.screen.photo.select

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose_study.ui.Loading
import com.example.compose_study.ui.ScrollToTopButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SelectPhotoScreen(
    toBack: () -> Unit,
    selectPhotos: (imageUrls: List<String>) -> Unit,
    viewModel: SelectPhotoViewModel
) {
    val photos = viewModel.pagingData.collectAsLazyPagingItems()
    val scrollState = rememberLazyGridState()
    val selectedPhotos by viewModel.selectedPhotos.collectAsState()
    val hasSelected by viewModel.hasSelected.collectAsState()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.scrollToTopEvent.collectLatest {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }
            }

            launch {
                viewModel.overSelectToastMessage.collectLatest {
                    Toast.makeText(context, "${SELECT_LIMIT}개 까지 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { toBack() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                    IconButton(
                        onClick = { selectPhotos(selectedPhotos.map { it.imageUrl }) }
                    ) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
                    }
                },
            )
        }
    ) { paddingValues ->
        if (photos.loadState.refresh is LoadState.Loading) {
            Loading()
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp, 8.dp),
            state = scrollState
        ) {
            items(
                count = photos.itemCount
            ) { index ->
                val photo = photos[index] ?: throw Throwable(message = "Invalid Photo Gallery")
                var isChecked by remember { mutableStateOf(photo.isChecked) }
                SelectPhotoItem(
                    photo = photo,
                    isChecked = isChecked,
                    onCheckedChange = { checked ->
                        if (hasSelected) isChecked = checked
                        viewModel.onPhotoClicked(photo)
                    }
                )
            }
        }

        if (photos.loadState.append is LoadState.Loading) {
            Loading()
        }

        ScrollToTopButton(
            modifier = Modifier.offset(y = 60.dp),
            onClick = { viewModel.scrollToTop() }
        )
    }
}
