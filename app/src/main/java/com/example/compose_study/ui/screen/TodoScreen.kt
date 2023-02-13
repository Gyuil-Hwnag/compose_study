package com.example.compose_study.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.item.DateItem
import java.util.*

@Composable
fun TodoScreen(
) {
    val dateList = mutableListOf<String>()
    for(i in 0 .. 14) {
        val date = Calendar.getInstance()
        date.time = Date()
        date.add(Calendar.DATE, i)
        dateList.add(date.time.getDateDay())
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        LazyRow(
            modifier = Modifier.fillMaxSize().padding(contentPadding).background(Color.Transparent),
            contentPadding = PaddingValues(16.dp, 8.dp)
        ) {
            items(
                items = dateList,
                itemContent = { DateItem(date = it, onClick = {} ) }
            )
        }
    }
}
