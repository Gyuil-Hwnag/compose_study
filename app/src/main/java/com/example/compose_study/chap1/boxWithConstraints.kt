package com.example.compose_study.chap1

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun BoxWithConstraintScreen() {
    BoxWithConstraintExample()
}

@Composable
fun BoxWithConstraintExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Inner(modifier = Modifier.widthIn(min = 100.dp, max = 350.dp).heightIn(min = 100.dp, max = 500.dp))
        Inner(modifier = Modifier.widthIn(min = 100.dp, max = 350.dp).heightIn(min = 100.dp, max = 140.dp))
        
    }
}

@Composable
fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        if (maxHeight > 150.dp) {
            Text(modifier = Modifier.align(Alignment.BottomCenter), text = "MaxHeight가 150을 넘은 경우에만 출력")
        }
        Text(text = "maxWidth : $maxWidth, maxHeight : $maxHeight, minWidth : $minWidth, minHeight: $minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxWithConstraintPreview() {
    ComposeStudyTheme {
        BoxWithConstraintScreen()
    }
}
