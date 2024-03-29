package com.example.compose_study.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_study.model.convertToCalendarDay
import com.example.compose_study.model.getCalendarDate
import com.example.compose_study.model.getDateDay
import com.example.compose_study.ui.screen.todo.TodoViewModel
import java.util.Calendar

@Composable
fun DateItem(
    viewModel: TodoViewModel = hiltViewModel(),
    date: String,
    onClick: (day: String) -> Unit
) {
    val dateTime = date.convertToCalendarDay()
    val isClicked = viewModel.currentDay.collectAsState().value == date
    val textColor = derivedStateOf { if(isClicked) Color.White else Color.Black }
    val backgroundColor = derivedStateOf { if(isClicked) Color.Black else Color.Transparent }

    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(align = Alignment.Center)
            .clickable { onClick(dateTime.getCalendarDate()) }
            .padding(top = 10.dp, bottom = 7.dp, start = 11.dp, end = 11.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dateTime.day.getDateDay(),
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier.width(32.dp).height(32.dp).clip(shape = CircleShape).background(backgroundColor.value),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dateTime.date.toString(),
                    fontSize = 12.sp,
                    color = textColor.value,
                    textAlign = TextAlign.Center,
                )
            }

            Text(
                text = if(dateTime.getCalendarDate() == Calendar.getInstance().time.getCalendarDate()) "오늘" else "",
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }
    }
}
