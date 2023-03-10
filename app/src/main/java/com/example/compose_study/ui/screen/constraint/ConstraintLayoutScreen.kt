package com.example.compose_study.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintLayoutContent(
    modifier: Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (button1, button2, text, button3, centerText) = createRefs()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button2")
        }

        val isCenter = remember { mutableStateOf(false) }
        Button(
            onClick = { isCenter.value = !isCenter.value },
            modifier = Modifier.constrainAs(button3) {
                top.linkTo(text.bottom, margin = 16.dp)
                start.linkTo(text.start)
                end.linkTo(text.end)
            }
        ) {
            Text(text = "Text To Center")
        }

        Text(
            text = if(isCenter.value) "Center Text" else "Not Center Text",
            modifier = Modifier.constrainAs(centerText) {
                top.linkTo(button3.bottom, margin = 16.dp)
                if(isCenter.value) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                } else {
                    start.linkTo(button3.start)
                    end.linkTo(button3.end)
                }
            }
        )
    }
}
