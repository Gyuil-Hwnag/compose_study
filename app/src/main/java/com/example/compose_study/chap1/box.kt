package com.example.compose_study.chap1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun BoxScreen() {
    BoxExample("Android")
}

@Composable
fun BoxExample(text: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.size(100.dp)) {
            Text(modifier = Modifier.align(Alignment.BottomCenter), text = "Hello $text!")
        }
        
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Box(modifier = Modifier.size(100.dp)) {
            Text(modifier = Modifier.align(Alignment.BottomEnd), text = "Hello $text!")
            Text(modifier = Modifier.align(Alignment.CenterEnd), text = "Compose!")
            Text(modifier = Modifier.align(Alignment.TopStart), text = "Hello World")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Box(modifier = Modifier.size(100.dp)) {
            Box(modifier = Modifier.size(70.dp).background(Color.Cyan).align(Alignment.CenterStart))
            Box(modifier = Modifier.size(70.dp).background(Color.Yellow).align(Alignment.BottomEnd))
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Box {
            Box(modifier = Modifier.fillMaxSize().background(Color.Cyan).align(Alignment.CenterStart))
            Box(modifier = Modifier.size(70.dp).background(Color.Yellow).align(Alignment.BottomEnd))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    ComposeStudyTheme {
        BoxScreen()
    }
}
