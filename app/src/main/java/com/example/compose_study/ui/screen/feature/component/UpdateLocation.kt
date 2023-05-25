package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun UpdateLocationScreen() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Divider()
        Location()
        Divider()
    }
}

@Composable
fun Divider() {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color(0xfff3f3f3)))
}

/**
 * 밑줄 관련 : https://stackoverflow.com/questions/75643210/how-to-change-the-textdecoration-underline-with-a-custom-style
 **/

@Composable
fun Location() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_location), contentDescription = "위치 아이콘")
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                text = "강남구 역삼동",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.drawBehind {
                    val strokeWidthPx = 8.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = Color.Yellow,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
            )
            Text(text = "기준으로 추천해드릴게요", fontSize = 14.sp, color = Color.Black,)
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            onClick = {},
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = "지역 변경", fontSize = 11.sp, color = Color.Black)
        }
    }
}

@Preview
@Composable
fun UpdateLocationPreview() {
    Compose_studyTheme {
        UpdateLocationScreen()
    }
}