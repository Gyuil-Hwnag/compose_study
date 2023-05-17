package com.example.compose_study.chap1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun RowScreen() {
    RowExample()
}

@Composable
fun RowExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.height(40.dp)) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(modifier = Modifier.align(Alignment.CenterVertically),text = "Second")
            Text(modifier = Modifier.align(Alignment.Bottom),text = "Third")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.Bottom) {
            Text(text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.Bottom) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.End) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.Start) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Row(modifier = Modifier.width(200.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(modifier = Modifier.align(Alignment.Top), text = "First")
            Text(text = "Second")
            Text(text = "Third")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        Row(modifier = Modifier
            .width(200.dp)
            .height(40.dp), horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.weight(3f), text = "First")
            Text(modifier = Modifier.weight(1f), text = "Second")
            Text(modifier = Modifier.weight(3f), text = "Third")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        Row(modifier = Modifier
            .width(200.dp)
            .height(40.dp), horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.weight(3f), text = "First", textAlign = TextAlign.Center)
            Icon(modifier = Modifier.weight(1f).background(Color.Cyan), imageVector = Icons.Filled.Add, contentDescription = "")
            Text(modifier = Modifier.weight(3f), text = "Third", textAlign = TextAlign.End)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Compose_studyTheme {
        RowScreen()
    }
}
