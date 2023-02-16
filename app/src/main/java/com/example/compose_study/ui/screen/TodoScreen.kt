package com.example.compose_study.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose_study.ui.item.DateItem
import com.example.compose_study.ui.item.TimeTableItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {

    val scrollState = rememberLazyListState()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.currentTimeIndex.collectLatest {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(it)
                    }
                }
            }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentPadding = PaddingValues(16.dp, 8.dp)
            ) {
                items(
                    items = viewModel.dateList,
                    itemContent = { DateItem(date = it, onClick = {} ) }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentPadding = PaddingValues(16.dp, 8.dp),
                state = scrollState
            ) {
                items(
                    items = viewModel.timeItems,
                    itemContent = { TimeTableItem(dateTime = it, onClick = {} ) }
                )
            }
        }
    }
}


