package com.example.compose_study.chap2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun ConstraintSetScreen() {
    ConstraintSetExample()
}

@Composable
fun ConstraintSetExample() {
    val constraintSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val yellowBox = createRefFor("yellowBox")
        val blueBox = createRefFor("blueBox")
        val greenBox = createRefFor("greenBox")

        constrain(redBox) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }

        constrain(yellowBox) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(blueBox) {
            centerTo(parent)
        }

        constrain(greenBox) {
            start.linkTo(blueBox.end, margin = 4.dp)
            top.linkTo(blueBox.bottom, margin = 4.dp)
        }
    }

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .layoutId("redBox")
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .layoutId("yellowBox")
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .layoutId("blueBox")
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Green)
            .layoutId("greenBox")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintSetPreview() {
    ComposeStudyTheme {
        ConstraintSetScreen()
    }
}
