package com.example.compose_study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun Screen() {
    Example()
}

@Composable
fun Example() {
    Column(modifier = Modifier.fillMaxSize()) {
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Compose_studyTheme {
        Screen()
    }
}
