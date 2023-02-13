package com.example.compose_study.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.ui.item.DateItem
import com.example.compose_study.ui.item.TimeTableItem

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {

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
                contentPadding = PaddingValues(16.dp, 8.dp)
            ) {
                items(
                    items = viewModel.timeItems,
                    itemContent = { TimeTableItem(dateTime = it, onClick = {} ) }
                )
            }
        }
    }
}
