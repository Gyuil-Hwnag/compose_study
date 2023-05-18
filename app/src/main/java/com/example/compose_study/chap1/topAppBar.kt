package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun TopAppBarScreen() {
    TopAppBarExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "TopAppBar") })

        TopAppBar(
            title = { Text(text = "TopAppBar") },
            navigationIcon = {
                Button(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            }
        )

        TopAppBar(
            title = { Text(text = "TopAppBar", modifier = Modifier.weight(1f)) },
            navigationIcon = {
                Button(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                   Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                }
            }
        )

        Text(text = "Hello Android")
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    Compose_studyTheme {
        TopAppBarScreen()
    }
}
