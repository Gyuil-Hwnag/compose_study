package com.example.compose_study.ui.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soywiz.klock.DateTime
import com.soywiz.klock.Time
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds

@Composable
fun TimeTableItem(dateTime: TimeItem, onClick: (day: Int) -> Unit) {
    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(align = Alignment.Center)
            .clickable { onClick(0) },
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight())
        {
            val alpha = if(dateTime.dateTime.minutes == 30) 0f else 1f
            Column {
                Text(
                    modifier = Modifier.alpha(alpha),
                    text = if(dateTime.dateTime.hours > 12) (dateTime.dateTime.hours-12).toString() else (dateTime.dateTime.hours).toString(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End
                )
                Text(
                    modifier = Modifier.alpha(alpha),
                    text = if(dateTime.dateTime.hours > 12) "PM" else "AM",
                    color = Color.Black,
                    fontSize = 10.sp,
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Divider(modifier = Modifier.height(1.dp), color = Color.LightGray)
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

data class TimeItem(
    val dateTime: DateTime,
    val span: TimeSpan
) {

    val hour: Int
        get() = dateTime.hours

    val minutes: Int
        get() = dateTime.minutes

    val range: ClosedRange<Time>
        get() = dateTime.time..(dateTime + (span - 1.milliseconds)).time

    val isDimmed: Boolean
        get() = dateTime < DateTime.nowLocal().local

}
