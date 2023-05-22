package com.example.compose_study.chap2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun ConstraintLayoutScreen() {
    ConstraintLayoutExample()
}

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, yellowBox, blueBox, greenBox) = createRefs()

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                bottom.linkTo(parent.bottom, margin = 8.dp)
                end.linkTo(parent.end, margin = 4.dp)
            }
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                // 1. top, bottom, start, end 모두 지정
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                // 2. centerTo로 정중앙으로 지정
//                centerTo(parent)

                // 3. 세로 중간, 가로 중간으로 지정
//                centerVerticallyTo(parent)
//                centerHorizontallyTo(parent)
            }
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                start.linkTo(blueBox.end, margin = 4.dp)
                top.linkTo(blueBox.bottom, margin = 4.dp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Compose_studyTheme {
        ConstraintLayoutScreen()
    }
}
