package com.example.compose_study.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.model.convertToDate
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.screen.TodoViewModel

@Composable
fun DateItem(
    viewModel: TodoViewModel = hiltViewModel(),
    date: String,
    onClick: (day: String) -> Unit
) {
    val dateTime = date.convertToDate()
    val isClicked = viewModel.currentDay.collectAsState().value == date
    val textColor = derivedStateOf { if(isClicked) Color.White else Color.Black }
    val backgroundColor = derivedStateOf { if(isClicked) Color.Black else Color.Transparent }

    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(align = Alignment.Center)
            .clickable { onClick(dateTime.getDateDay()) }
            .padding(horizontal = 4.dp, vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.clip(shape = CircleShape).background(backgroundColor.value).padding(horizontal = 16.dp, vertical = 4.dp).wrapContentSize()
        ) {
            Text(
                text = dateTime.day.getDateDay(),
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = textColor.value
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dateTime.date.toString(),
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = textColor.value
            )
        }
    }
}
