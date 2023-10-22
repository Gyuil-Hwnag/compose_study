package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun ColumnScreen() {
    ColumnExample()
}

@Composable
fun ColumnExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.height(100.dp)) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Column(modifier = Modifier.height(100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Column(modifier = Modifier.height(100.dp), horizontalAlignment = Alignment.End) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Column(modifier = Modifier.height(100.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Column(modifier = Modifier.height(100.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        Column(modifier = Modifier.height(100.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
            Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "First")
            Text(text = "Second")
            Text(modifier = Modifier.align(Alignment.Start),text = "Third")
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    ComposeStudyTheme {
        ColumnScreen()
    }
}
