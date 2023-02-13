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
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.item.DateItem
import com.example.compose_study.ui.item.TimeItem
import com.example.compose_study.ui.item.TimeTableItem
import com.soywiz.klock.*
import java.util.*
import java.util.Date

@Composable
fun TodoScreen(
) {
    val dateList = mutableListOf<String>()
    val date = Calendar.getInstance()
    date.time = Date()

    for(i in 0 .. 14) {
        dateList.add(date.time.getDateDay())
        date.add(Calendar.DATE, 1)
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
                    items = dateList,
                    itemContent = { DateItem(date = it, onClick = {} ) }
                )
            }

            val timeItems = (0..23)
                .flatMap { hour ->
                    listOf(
                        TimeItem(dateTime = DateTime.now().date + Time(hour.hours), span = 30.minutes),
                        TimeItem(dateTime = DateTime.now().date + Time(hour.hours + 30.minutes), span = 30.minutes)
                    )
                }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentPadding = PaddingValues(16.dp, 8.dp)
            ) {
                items(
                    items = timeItems,
                    itemContent = { TimeTableItem(dateTime = it, onClick = {} ) }
                )
            }
        }
    }
}
