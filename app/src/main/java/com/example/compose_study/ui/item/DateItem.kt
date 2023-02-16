package com.example.compose_study.ui.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.model.convertToDate
import com.example.compose_study.model.getDateDay

@Composable
fun DateItem(date: String, onClick: (day: String) -> Unit) {
    val dateTime = date.convertToDate()
    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(align = Alignment.Center)
            .clickable { onClick(dateTime.getDateDay())},
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(text = dateTime.day.getDateDay(), fontSize = 12.sp, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = dateTime.date.toString(), fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
        }
    }
}
