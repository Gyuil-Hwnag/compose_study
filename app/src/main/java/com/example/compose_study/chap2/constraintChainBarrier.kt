package com.example.compose_study.chap2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun ConstraintChainBarrierScreen() {
    ConstraintChainBarrierExample()
}

@Composable
fun ConstraintChainBarrierExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, yellowBox, blueBox, greenBox, text) = createRefs()

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(parent.top, margin = 32.dp)
            }
        )

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(parent.top, margin = 24.dp)
            }
        )


//        createVerticalChain(redBox, yellowBox, blueBox)
//        createHorizontalChain(redBox, yellowBox, blueBox)

//        createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.Spread)
//        createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.Packed)
        createHorizontalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.SpreadInside)

//        createVerticalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.Spread)
//        createVerticalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.Packed)
//        createVerticalChain(redBox, yellowBox, blueBox, chainStyle = ChainStyle.SpreadInside)

        val barrier = createBottomBarrier(redBox, yellowBox, blueBox)
        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(barrier)
            },
            text = "Text Field 내용입니다.Text Field 내용입니다.Text Field 내용입니다.Text Field 내용입니다.Text Field 내용입니다.Text Field 내용입니다.Text Field 내용입니다.",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintChainBarrierPreview() {
    Compose_studyTheme {
        ConstraintChainBarrierScreen()
    }
}
