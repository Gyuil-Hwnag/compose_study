package com.example.compose_study.ui.screen.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun DrawScreen() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.Yellow,
                    shape = RoundedCornerShape(8.dp)
                )
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_draw), contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "djdklfdkjdfk")
            }
        }
        Row {
            Spacer(modifier = Modifier.width(12.dp))
            BubbleBottomArrow()
        }
    }
}

@Composable
fun BubbleBottomArrow() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        onDraw = {
            val path = Path().apply {
                moveTo(0.dp.toPx(), 0.dp.toPx())
                lineTo(12.dp.toPx(), 12.dp.toPx())
                lineTo(24.dp.toPx(), 0.dp.toPx())
            }

            drawPath(
                path = path,
                color = Color.Yellow
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeStudyTheme {
        DrawScreen()
    }
}
