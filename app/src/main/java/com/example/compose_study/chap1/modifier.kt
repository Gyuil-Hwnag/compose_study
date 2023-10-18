package com.example.compose_study.chap1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun ModifierScreen() {
    ModifierExample()
}

@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            modifier = Modifier.fillMaxSize(),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            modifier = Modifier.height(100.dp),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            modifier = Modifier
                .size(width = 164.dp, height = 84.dp)
                .background(Color.Red),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }
        Button(
            modifier = Modifier.size(width = 164.dp, height = 84.dp),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Cyan
            ),
            modifier = Modifier.size(200.dp),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Cyan
            ),
            modifier = Modifier.size(200.dp).padding(30.dp),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Search")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Cyan
            ),
            modifier = Modifier.size(200.dp).padding(30.dp),
            onClick = {},
            enabled = false
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(modifier = Modifier.clickable {}, text = "Search")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Cyan
            ),
            modifier = Modifier.size(200.dp).padding(30.dp),
            onClick = {},
        ) {
            Icon(modifier = Modifier.background(Color.Blue),imageVector = Icons.Filled.Search, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing).background(Color.Black))
            Text(modifier = Modifier.offset(x = 10.dp, y = (-10).dp), text = "Search")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ModifierPreview() {
    ComposeStudyTheme {
        ModifierScreen()
    }
}
