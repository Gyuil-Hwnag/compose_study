package com.example.compose_study.chap1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun SurfaceScreen() {
    SurfaceExample("Android")
}

@Composable
fun SurfaceExample(text: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp,
            border = BorderStroke(width = 2.dp, color = Color.Magenta)
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp,
            border = BorderStroke(width = 2.dp, color = Color.Magenta),
            shape = CircleShape
        ) {
            Text(modifier = Modifier.padding(12.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp,
            border = BorderStroke(width = 2.dp, color = Color.Magenta),
            shape = CircleShape,
            color = androidx.compose.material.MaterialTheme.colors.primary
        ) {
            Text(modifier = Modifier.padding(12.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp,
            border = BorderStroke(width = 2.dp, color = Color.Magenta),
            shape = CircleShape,
            color = androidx.compose.material.MaterialTheme.colors.error
        ) {
            Text(modifier = Modifier.padding(12.dp), text = "Hello $text!")
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            elevation = 10.dp,
            border = BorderStroke(width = 2.dp, color = Color.Magenta),
            shape = CircleShape,
            color = androidx.compose.material.MaterialTheme.colors.secondary
        ) {
            Text(modifier = Modifier.padding(12.dp), text = "Hello $text!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurfacePreview() {
    Compose_studyTheme {
        SurfaceScreen()
    }
}
